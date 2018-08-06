package nlab.practice.reactivetest

import io.reactivex.Observable
import io.reactivex.rxkotlin.zipWith
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * 기타 유틸 관련 학습테스트 정의
 *
 * @author Doohyun
 * @since 2018. 08. 06
 */
class EtcUtilTest {

    /**
     * Delay 시간을 받아, 발행을 지연시키는 함수
     *
     * -> Delay 의 경우 메인스레드에서 실행하지 않음
     */
    @Test
    fun doDelay() {
        Observable.fromArray(1, 2, 3, 4)
                .delay(2, TimeUnit.SECONDS)
                .subscribe { println(it) }

        Thread.sleep(5000)
    }

    /**
     * 이전 값의 발행 이후 시간이 얼마나 흘렀는지 알려줌
     */
    @Test
    fun doTimeInterval() {
        Observable.fromArray(1, 2, 3, 4)
                .map { it.toString() }
                .zipWith(Observable.interval(1, TimeUnit.SECONDS)) {
                    number : String, _
                    ->
                    number
                }
                .doOnNext { println("데이터 출력 [$it]") }
                .timeInterval()
                .doOnNext { println("흘러간 시간 [${it.time(TimeUnit.MILLISECONDS)}ms]")}
                .subscribe()


        Thread.sleep(5000)
    }
}

