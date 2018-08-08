package nlab.practice.reactivetest

import io.reactivex.Observable
import io.reactivex.rxkotlin.zipWith
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * 흐름 제어 관련 테스트 케이스 제작
 *
 * @author Doohyun
 */
class StreamControlUnitTest {

    /**
     * Sample : 특정 시간 동안 여러 데이터가 들어왔을때 마지막 데이터만 발행하고 나머지는 무시
     */
    @Test
    fun doSampleTest() {

        // 1초에 한번씩 샘플링하는 흐름 정의
        Observable.merge(
                    Observable.fromArray(1,2,3,4)
                            .map { it.toString() }
                            .zipWith(Observable.interval(500, TimeUnit.MILLISECONDS)) {
                                number, _ -> number
                            },

                    Observable.fromArray("A", "B", "C")
                            .zipWith(Observable.interval(400, TimeUnit.MILLISECONDS)) {
                                number, _ -> number
                            }

                )
                .sample(1, TimeUnit.SECONDS, true)
                .subscribe { println(it) }

        Thread.sleep(50000)
    }

    /**
     * throttleFirst : 샘플링 결과에서 가장 먼저 추출된 결과를 추출한다.
     */
    @Test
    fun doThrottleFirst() {
        Observable.range(0, 20)
                .zipWith(Observable.interval(500, TimeUnit.MILLISECONDS)) {
                    number, _ -> number
                }
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe { println(it) }

        Thread.sleep(50000)
    }


    /**
     * throttleLast : 샘플링 결과에서 가장 마지막 추출된 결과를 추출한다.
     */
    @Test
    fun doThrottleLast() {
        Observable.range(0, 20)
                .zipWith(Observable.interval(500, TimeUnit.MILLISECONDS)) {
                    number, _ -> number
                }
                .throttleLast(1, TimeUnit.SECONDS)
                .subscribe { println(it) }

        Thread.sleep(50000)
    }

    /**
     * Buffer : 일정 시간 동안 데이터를 모아두었다가 한꺼번에 발행해주기 때문에, 넘치는 데이터 흐름을 제어할때 사용
     */
    @Test
    fun doBuffer() {
        Observable.range(0, 20)
                .zipWith(Observable.interval(500, TimeUnit.MILLISECONDS)) {
                    number, _ -> number
                }
                .buffer(3)
                .subscribe { println("모아진 데이터 $it") }

        Thread.sleep(50000)
    }

    /**
     * window : 특정 조건에 맞는 입력값들을 그룹화 해서 별도의 Observable을 병렬로 만듦
     */
    @Test
    fun doWindow() {
        Observable.range(0, 20)
                .window(5)
                .subscribe {
                    println("Print Group observable")
                    it.subscribe { println(it) }
                }

        Thread.sleep(50000)
    }

    /**
     * debounce : timeout 으로 시간보다 긴 데이터만 사용하도록 처리
     *
     * 마지막 데이터는 무조건 출력하는 것으로 확인
     */
    @Test
    fun doDeBounce() {
        Observable.range(0, 20)
                .zipWith(Observable.interval(1001, TimeUnit.MILLISECONDS)) {
                    number, _ -> number
                }
                .debounce(1, TimeUnit.SECONDS)
                .subscribe { println(it) }

        Thread.sleep(50000)
    }

}