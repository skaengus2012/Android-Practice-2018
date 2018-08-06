package nlab.practice.reactivetest

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * 조건 연산자 관련 Rx 학습 테스트
 *
 * @author Doohyun
 * @since 2018. 08. 06
 */
class ConditionExecutionUnitTest {

    /**
     * amb : 둘 중 먼저 데이터를 발행하는 Observable 을 선택
     */
    @Test
    fun doAmb() {
        val observables : Observable<String> =
                Observable.ambArray(
                        Observable.fromArray(1, 2, 3)
                                .map { it.toString() }
                                .zipWith(Observable.interval(1, TimeUnit.SECONDS), BiFunction { number, _ ->  number }),

                        Observable.fromArray(4, 5, 6)
                                .map { it.toString() }
                                .zipWith(Observable.interval(500, TimeUnit.MILLISECONDS), BiFunction { number, _ ->  number })
                )

        observables.subscribe { println(it) }
        Thread.sleep(5000)
    }

    /**
     * takeUntil : take 함수처럼 일정 개수만 발행, 발행 중지 후, 즉시 완료.
     */
    @Test
    fun doTakeUtil() {
        val observableSource: Observable<String> =
            Observable.fromArray(1, 2, 3)
                    .map { it.toString() }
                    .zipWith(Observable.interval(2, TimeUnit.SECONDS), BiFunction { number, _ ->  number })

        observableSource
                .takeUntil(Observable.timer(3, TimeUnit.SECONDS))
                .subscribe { println(it) }
        Thread.sleep(5000)
    }

    /**
     * skipUntil : takeUtil 과의 반대 기능 -> 무시하다가 설정된 시간부터 발행
     */
    @Test
    fun doSkipUtil() {
        val observableSource: Observable<String> =
                Observable.fromArray(1, 2, 3, 4)
                        .map { it.toString() }
                        .zipWith(Observable.interval(2, TimeUnit.SECONDS), BiFunction { number, _ ->  number })

        observableSource
                .skipUntil(Observable.timer(4, TimeUnit.SECONDS))
                .subscribe { println(it) }
        Thread.sleep(10000)
    }
}