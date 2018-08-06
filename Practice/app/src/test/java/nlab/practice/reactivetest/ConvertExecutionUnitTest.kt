package nlab.practice.reactivetest

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * 변환 연산자에 대한 유닛 테스트
 *
 * 변환 연산자 : 변환 연사자는 데이터 흐름을 마음대로 변형할 수 있는 연산자들.
 *
 * @author Doohyun
 * @since 2018. 07. 20
 */
class ConvertExecutionUnitTest {

    /**
     * concatMap : 먼저 들어온 데이터 순서대로 처리를 보장하는 함수
     */
    @Test
    fun doConcatMap() {
        val numbers = arrayOf(1,3,5)

        Observable.interval(4, TimeUnit.SECONDS)
                .take(numbers.size.toLong())
                .map { numbers[it.toInt()] }
                .concatMap { number -> Observable.interval(2, TimeUnit.SECONDS).take(2).map { number } }
                .subscribe { println(it) }

        Thread.sleep(30000)
    }

    /**
     * flatMap : 순서를 보장하지는 않음.
     */
    @Test
    fun doFlatMap() {
        val numbers = arrayOf(1,3,5)

        Observable.interval(1, TimeUnit.SECONDS)
                .take(numbers.size.toLong())
                .map { numbers[it.toInt()] }
                .flatMap { number -> Observable.interval(6, TimeUnit.SECONDS).take(2).map { number } }
                .subscribe { println(it) }

        Thread.sleep(30000)
    }

    /**
     * SwitchMap : 순서 보장에 있어서 concatMap 과 비슷하지만, 순서를 맞추기 위한 중간 데이터는 중단하고 새 데이터를 실행.
     *
     * 마지막 데이터 실행은 보장
     */
    @Test
    fun doSwitchMap() {
        val numbers = arrayOf(1,3,5)

        Observable.interval(1, TimeUnit.SECONDS)
                .take(numbers.size.toLong())
                .map { numbers[it.toInt()] }
                .switchMap { number -> Observable.interval(6, TimeUnit.SECONDS).take(2).map { number } }
                .subscribe { println(it) }

        Thread.sleep(30000)
    }


    /**
     * Concat 형태 Single 데이터를 묶어서 처리
     *
     * 첫번째 Single 이 처리되고, 두번째가 처리됨이 보장
     */
    @Test
    fun doSingleOnConcatWith() {
        val concatObservable : Single<Int> =
                Single.fromCallable {
                    Thread.sleep(3000)
                    5
                }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())


        Single.fromCallable {
                    Thread.sleep(2000)
                    1
                }
                .concatWith { concatObservable.subscribe(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribe { println(it) }

        Thread.sleep(10000)
    }

    /**
     * Scan 은 reduce 와 기능이 비슷하지만, 중간 과정의 데이터를 Stream 으로 넘겨주는 형태
     */
    @Test
    fun doScan() {
        Observable.fromArray(1,2,3,4,5)
                .scan(0 ) {
                    n1, n2
                    ->
                    n1 + n2
                }
                .subscribe { println("누적계 테스트 : $it") }
    }

    /**
     * 2개 이상의 Observable 을 결합할 때 사용, 한쪽의 Observable 이 끝나지 않는다면 처리될 때까지 발행을 기다림
     *
     * -> folk & join library 와 비슷한 감이 있음.
     */
    @Test
    fun doZip() {
        val stringObservable : Observable<String> =
                Observable.create {
                    emitter
                    ->
                    arrayOf("A", "B", "C", "D").forEach {
                        Thread.sleep(1000)
                        println("문자 발행 $it")
                        emitter.onNext(it)
                    }

                    emitter.onComplete()
                }

        val numberObservable : Observable<Int> =
                Observable.create {
                    emitter
                    ->
                    arrayOf(1, 2, 3, 4, 5).forEach {
                        Thread.sleep(1500)
                        println("숫자 발행 $it")
                        emitter.onNext(it)
                    }

                    emitter.onComplete()
                }

        val zipObservable : Observable<String> =
                Observable.zip(
                        stringObservable.subscribeOn(Schedulers.io()),
                        numberObservable.subscribeOn(Schedulers.io()),
                        BiFunction { t1, t2 -> "합쳐진 결과 : $t1 $t2" }
                )
        zipObservable
                .observeOn(Schedulers.trampoline())
                .subscribe { println(it) }

        Thread.sleep(20000)
    }

    /**
     * combineLatest 두 개 이상의 Observable 을 바탕으로 각각의 값이 변경될 때, 마지막 데이터를 바탕으로 묶어주는 함수.
     */
    @Test
    fun doCombineLatest() {
        val combineSource : Observable<String> =
                Observable.combineLatest(
                        Observable.fromArray(1,2,3,4,5).zipWith(Observable.interval(1, TimeUnit.SECONDS), BiFunction {number, _ -> number}),
                        Observable.fromArray("A", "B","C", "D").zipWith(Observable.interval(2, TimeUnit.SECONDS), BiFunction {str, _ -> str}),
                        BiFunction { t1, t2 ->  "합쳐진 결과 : $t1 $t2"}
                )

        combineSource.subscribe { println(it) }
        Thread.sleep(20000)
    }

    /**
     * 가장 단순한 결합 함수로, 아무것도 관여하지 않고 먼저 입력되는 데이터를 그대로 발행
     */
    @Test
    fun doMerge() {
        val mergeSource : Observable<String> =
                Observable.merge(
                        Observable.fromArray(1,2,3,4,5).zipWith(Observable.interval(1, TimeUnit.SECONDS), BiFunction {number, _ -> number.toString()}),
                        Observable.fromArray("A", "B","C", "D").zipWith(Observable.interval(2, TimeUnit.SECONDS), BiFunction {str, _ -> str})
                )

        mergeSource.observeOn(Schedulers.trampoline()).subscribe{ println(it) }
        Thread.sleep(20000)
    }

    /**
     * 첫번째 Observable 이 끝나야, 두번째 Observable 을 수행
     */
    @Test
    fun doConcat() {
        val mergeSource : Observable<String> =
                Observable.concat (
                        Observable.fromArray(1,2,3,4,5).zipWith(Observable.interval(1, TimeUnit.SECONDS), BiFunction {number, _ -> number.toString()}),
                        Observable.fromArray("A", "B","C", "D").zipWith(Observable.interval(2, TimeUnit.SECONDS), BiFunction {str, _ -> str})
                )

        mergeSource.observeOn(Schedulers.trampoline()).subscribe{ println(it) }
        Thread.sleep(20000)
    }

    /**
     * Concat 다른 Observable 생성 방식
     */
    @Test
    fun doConcatCreate() {
        val mergeSource2 : Observable<String> =
                Observable.concat (Observable.create {
                    it.onNext(Observable.fromArray(1,2,3,4,5).zipWith(Observable.interval(1, TimeUnit.SECONDS), BiFunction {number, _ -> number.toString()}))
                    it.onNext(Observable.fromArray("A", "B","C", "D").zipWith(Observable.interval(2, TimeUnit.SECONDS), BiFunction {str, _ -> str}))
                })

        mergeSource2.observeOn(Schedulers.trampoline()).subscribe { println(it) }
        Thread.sleep(20000)
    }
}