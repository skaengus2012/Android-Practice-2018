package nlab.practice.reactivetest

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import nlab.practice.db.model.reactive.StringMessage
import org.junit.Before
import org.junit.Test

/**
 * Reactive X 에 대한 심플 예제 열람
 *
 * @author Doohyun
 * @since 2018. 07. 16
 */
class SimpleReactiveXUnitTest {

    /**
     * Kotlin 에서는 람다 사용을 위해, 인터페이스를 지정할 필요가 없음 (IConsumer, IExConsumer 등)
     *
     * 이 의미는 람다 내부의 Checked 예외에서 이득을 볼 수 있음
     * ex. 기존의 reactiveX 쪽 함수형 인터페이스들은 모든 메소드에 Checked 예외를 throw 하도록 함 -> 일반적인 람다 사용 시 예외 처리해야함
     *     -> 코틀린에서는 자체 람다식으로 해당 모든 인터페이스들을 추상화하고 있음
     *
     * 아래는 이와 관련된 예제를 기록함
     */
    @Test
    fun doEffectiveCallback() {
        // string 을 출력하는 컨슈머 정의
        val printStringConsumer : (String) -> Unit = { println(it) }

        // 일반 호출에서 예외 처리를 하지 않음
        printStringConsumer("Doohyun")

        Single.just("[ReactiveX] Doohyun").subscribe(printStringConsumer)
    }

    /**
     * 옵저버블에서 다른 형식으로 변경하는 예제 정의
     *
     * - 시간차를 고려하여 던지기
     *
     * -> 기본적으로 onNext 의 데이터를 받아 Single 로 변형
     * -> skip 을 이용하여 single 제작을 고려할 경우, 옵저버쪽에서 받은 데이터의 갯수로 처리함을 알 수 있음 (skip(1) 을 할 경우, 첫번째 sleep 이 먹고 있음)
     */
    @Test
    fun doObservableToSingle() {

        println("Start Test")

        val observableToSingle =
                Observable.create{ emitter : ObservableEmitter<String>
                    ->
                    Thread.sleep(2000)
                    emitter.onNext("Observable to Single")

                    // first 로 보내기 때문에 처음의 next 만 스킵
                    Thread.sleep(2000)
                    emitter.onNext("Observable to Single -> next 2")

                    emitter.onComplete()
                }

        val defaultMessage = "default single item"
        val printConsumer : (String) -> Unit = { println(it) }

        // 3회까지 skip 하여 single 만들기
        for (i in 0..2L) {
            observableToSingle.skip(i)
                    .first(defaultMessage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.trampoline())
                    .subscribe (printConsumer)
        }

        Thread.sleep(5000)
    }

    private lateinit var completableTarget : Observable<StringMessage>

    /**
     * Completable 로 변경할 Observable 초기화
     */
    @Before
    fun initObservableToCompletable() {
        val sleepTimes = arrayOf(1000L, 2000, 3000)

        completableTarget =
                Observable.create{ emitter : ObservableEmitter<StringMessage>
                    ->
                    for (i in 0 until sleepTimes.size) {
                        Thread.sleep(sleepTimes[i])
                        emitter.onNext(StringMessage(message = "Observable to Complete -> next $i", sleepTime = sleepTimes[sleepTimes.size - (i + 1)]))
                    }

                    emitter.onComplete()
                }
    }

    /**
     * Observable to Completable (Ordered)
     *
     * - concatMap 을 사용. 변경된 Completable 이 완료되어야 다음 Completable 을 수행하는 방식
     * - 즉, 데이터 도착 후 처리가 완료되어야 다음 이벤트 처리가 이루어짐
     */
    @Test
    fun doObservableToCompletableOrdered() {
        completableTarget
                .subscribeOn(Schedulers.io())
                .concatMapCompletable { createMessagePrinterCompletable(it) }
                .subscribe()

        Thread.sleep(10000)
    }

    /**
     * Observable to Completable (None Ordered)
     *
     * - merge 을 사용.
     * - 이벤트 처리가 완료하지 않아도 다음 이벤트를 바로 받아 처리
     */
    @Test
    fun doObservableToCompletableNoneOrdered() {
        completableTarget
                .subscribeOn(Schedulers.io())
                .flatMapCompletable { createMessagePrinterCompletable(it) }
                .subscribe()

        Thread.sleep(10000)
    }

    /**
     * [strMessage] 을 출력하는 Completable 를 생산
     *
     * @param strMessage
     * @return
     */
    private fun createMessagePrinterCompletable(strMessage : StringMessage) : Completable =
            Completable.fromAction {
                        println("Data received. Sleep...")
                        Thread.sleep(strMessage.sleepTime)
                        println("Print [${strMessage.message}]. Job complete")
                    }
                    .subscribeOn(Schedulers.io())
}