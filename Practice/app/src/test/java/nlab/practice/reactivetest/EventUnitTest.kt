package nlab.practice.reactivetest

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.zipWith
import org.junit.Before
import org.junit.Test
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
}