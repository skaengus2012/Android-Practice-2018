package nlab.practice.reactivetest

import io.reactivex.Observable
import io.reactivex.subjects.*
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * 구독자 없이도 데이터를 발행하는 스트림 테스트
 *
 * Hot Observable : 구독자의 존재 여부 없이 데이터를 발행
 * Cold Observable : 구독자가 subscribe 하기 전까지 데이터를 발행하지 않음
 *
 * @author Doohyun
 * @since 2018. 07. 17
 */
class HotObservableUnitTest {

    /**
     * AsyncSubject 에 대한 테스트
     *
     * onComplete 실행 후, 가장 최신 데이터만 발행
     */
    @Test
    fun doAsyncSubject() = doSubjectProcess(AsyncSubject.create())

    /**
     * BehaviorSubject 를 이용한 테스트
     *
     * 가장 최근 값 혹은 디폴트 값을 출력한다.
     */
    @Test
    fun doBehaviorSubject() = doSubjectProcess(BehaviorSubject.createDefault("5"))

    /**
     * 오직 해당 시간에 발생한 데이터를 구독자에게 전달
     */
    @Test
    fun doPublishSubject() = doSubjectProcess(PublishSubject.create())

    /**
     * 구독자가 생기면 처음부터 데이터를 전달
     */
    @Test
    fun doReplaySubject() = doSubjectProcess(ReplaySubject.create())

    /**
     * [subject] 에 Observe 를 달고 Print 하는 테스트
     *
     * @param subject
     */
    private fun doSubjectProcess(subject: Subject<String>) {
        subject.subscribe{ println("Subscribe #1 => $it, CurrentThread [${Thread.currentThread()}]") }
        subject.onNext("1")
        subject.onNext("2")
        subject.subscribe { println("Subscribe #2 => $it") }
        subject.onNext("3")
        subject.onComplete()
    }

    /**
     * Code Observable 을 Hot Observable 로 변경시키는 방법
     *
     * 구독시점부터의 데이터가 전달
     */
    @Test
    fun doConnectableObservable() {
        // interval 자체는 다른 스레드에서 실행하는 것으로 보임
        val intervalObservable =
                Observable.interval(1, TimeUnit.SECONDS).take(10)


        // Convert Cold to Hot!
        val connectedObservable = intervalObservable.publish()
        connectedObservable.subscribe { println("Subscribe #1 => $it, CurrentThread [${Thread.currentThread()}]") }
        connectedObservable.subscribe { println("Subscribe #2 => $it") }

        Thread.sleep(2000)
        println("[Connect 시작] 데이터 발행 시작")
        // connect 가 불리면, 그 시간부터 데이터를 전달 시작
        connectedObservable.connect()

        // connect 가 불린 이 후부터는 PublishSubject 와 같은 기능을 수행하는 것으로 보임.
        Thread.sleep(2000)
        connectedObservable.subscribe { println("Subscribe #3 => $it") }

        Thread.sleep(100000)
    }
}