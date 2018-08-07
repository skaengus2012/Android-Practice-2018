package nlab.practice.reactivetest

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.zipWith
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Rx 의 여러 이벤트에 대한 콜백 테스트
 *
 * @author Doohyun
 * @since 2018. 08. 06
 */
class EventUnitTest {

    private lateinit var _preSetObservable : Observable<Int>

    @Before
    fun initPreSetObservable() {
        _preSetObservable =
                Observable.fromArray(1,2,3,4,5)
                    .zipWith(
                            Observable.interval(500, TimeUnit.MILLISECONDS)
                    ) {
                        number, _
                        ->
                        number
                    }
                    .doOnNext { println("doOnNext : $it") }
                    .doOnComplete { println("doOnComplete") }
                    .doOnSubscribe { println("doOnSubscribe -> isDisposed ${it.isDisposed}") }
                    .doOnDispose { println("doOnDispose") }
    }

    /**
     * OnComplete 가 동장할때까지 수행
     */
    @Test
    fun doEventTestUntilOnComplete() = _preSetObservable.let {
        it.subscribe()
        Thread.sleep(5000)
    }

    /**
     * 1초 후에 구독 취소
     */
    @Test
    fun doEventTestStopSubscribe() = _preSetObservable.let {
        val compositeDisposable =
                CompositeDisposable().apply { it.subscribe().addTo(this) }

        Thread.sleep(1000)
        compositeDisposable.clear()
    }

    /**
     * 에러에 대한 리턴함수 정의.
     */
    @Test
    fun doEventTestOnErrorReturn() {
        Observable.fromArray("1", "2", "3", "A")
                .map { Integer.parseInt(it) }
                .onErrorReturn {
                    it.printStackTrace()

                    val isNumberFormatError = (it is NumberFormatException)
                    if (isNumberFormatError) {
                        -1
                    } else {
                        // 무한루프에 걸리지는 않는듯
                        throw it
                    }
                }
                .doOnNext { println("doOnNext : $it") }
                .doOnError { println("doOnError") }
                .subscribe()
    }

    /**
     * 에러에 대한 리턴함수 정의.
     *
     * onErrorReturn 과 비슷하나, 예외처리문을 작성할 필요없이 실패 시, 에러만 출력
     */
    @Test
    fun doEventTestOnErrorReturnItem() {
        Observable.fromArray("1", "2", "3", "A")
                .map { Integer.parseInt(it) }
                .onErrorReturnItem(-1)
                .doOnNext { println("doOnNext : $it") }
                .doOnError { println("doOnError") }
                .subscribe()
    }

    /**
     * 에러에 대한 리턴함수 정의.
     *
     * onErrorReturn 과 비슷하나, 예외 시, 원하는 Observable 로 대체
     */
    @Test
    fun doEventTestOnErrorResumeNext() {
        Observable.fromArray("1", "2", "3", "A")
                .map { Integer.parseInt(it) }
                .onErrorResumeNext ( Observable.fromArray(10, 11, 12) )
                .doOnNext { println("doOnNext : $it") }
                .doOnError { println("doOnError") }
                .subscribe()
    }

    /**
     * 재시도를 수행하는 구문 실행
     */
    @Test
    fun doRetryProcess() {
        val random = Random()
        val maxRetryCount = 5

        Completable.fromAction {
                    val randomValue = random.nextInt(2)
                    if (randomValue == 0) {
                        println("예외 발행")
                        throw RuntimeException()
                    } else {
                        println("프로세스 성공")
                    }
                }
                .retry {
                    retryCount, _
                    ->
                    println("재시도 수행")
                    maxRetryCount > retryCount
                }
                .subscribe { println("프로세스 완료") }
    }

    /**
     * error 를 데이터 흐름으로 받아 처리하는 방식
     *
     * error 의 데이터 흐름을 변형하여,
     * onError 를 준다면 실행 중지, 보통은 timer 를 이용하여 언제 후 재시도할지 결정하는듯
     */
    @Test
    fun doRetryWhen() {
        val random = Random()

        Completable.fromAction {
                    val randomValue = random.nextInt(3)
                    when(randomValue) {
                        0 -> {
                            println("예외 발행 - 0")
                            throw IOException()
                        }

                        1 -> {
                            println("예외 발행 - 1")
                            throw NullPointerException()
                        }

                        else -> println("프로세스 성공")
                    }
                }
                .retryWhen {
                    it.flatMap {
                        throwable
                        ->
                        if (throwable is NullPointerException) {
                            println("2000ms 뒤에 재시도")
                            Flowable.timer(2, TimeUnit.SECONDS)
                        } else {
                            println("재시도 중지")
                            // Kotlin Type Checker 때문에 꼭 변수명을 만들어줘야 하는 듯
                            val flowAble : Flowable<Any> = Flowable.error(throwable)
                            flowAble
                        }
                    }
                }
                .subscribe { println("프로세스 완료") }

        Thread.sleep(10000)
    }
}