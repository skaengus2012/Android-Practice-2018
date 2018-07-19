package nlab.practice.reactivetest

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * 기타 유틸관련 유닛테스트 정의
 *
 * @author Doohyun
 * @since 2018. 07. 19
 */
class EtcReactiveXUnitTest {

    /**
     * Observable 의 지연초기화 (lazy) 를 반영한 기능
     *
     * Subscribe 를 호출해야 Observable 을 만들 수 있음
     */
    @Test
    fun doDefer() {
        val single = Single.defer{
            // 만들 때 지연을 한다 생각
            Thread.sleep(3000)
            println("Observable 생성 ${Thread.currentThread()}")

            Single.fromCallable { "지연 초기화 결과 출력" }
        }

        println("테스트 수트 실행")
        Thread.sleep(1000)

        single.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribe { message
                    ->
                    println(message)
                }

        Thread.sleep(10000)
    }

    /**
     * 출력 Stream 에 대하여, 지정한 횟수만큼 반복한다.
     *
     * repeat 에 넘버를 지정하지 않을 경우, 무한 반복 실행
     */
    @Test
    fun doRepeat() {
        val numbers =
                Observable.fromArray(1, 2, 3)
                    .repeat(3)
                    .toList()
                    .blockingGet()

        for (i in numbers) {
            println(i)
        }
    }

    /**
     * 5초마다 특정 행위를 수행하는 스케줄러를 제작
     *
     * Timer 는 compute 스레드에서 행위를 수행
     */
    @Test
    fun doScheduler() {
        Observable.timer(5, TimeUnit.SECONDS)
                .repeat()
                .subscribe{ println("특정 행위를 수행 ${Thread.currentThread()}") }

        Thread.sleep(100000000)
    }
}