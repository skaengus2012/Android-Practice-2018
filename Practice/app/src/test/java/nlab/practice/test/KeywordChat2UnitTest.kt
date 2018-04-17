package nlab.practice.test

import nlab.practice.model.operatorTest.OperatorSupportList
import org.junit.Test

/**
 * Kotlin keyword 관련 추가적인 테스트 슈트 정의
 *
 * 참고 URL
 *  -> https://github.com/skaengus2012/Android-Practice-2018/issues/4
 *
 * @author ndh1002
 */
class KeywordChat2UnitTest {

    /**
     * 재귀함수 최적화 테스트.
     */
    @Test
    fun testRecursive() {
        val factorialResult = getFactorialResult(5)
        println(factorialResult)
    }

    /**
     * 팩토리얼 구현.
     *
     * [number] 에 해당하는 팩토리얼 값 계산.
     *
     * tailrec 키워드 사용 시, 스택오버플로 걱정이 없는 빠르고 효율적인 루프 기반 버전으로 컴파일러가 최적화해준다.
     *
     * @param number
     */
    private tailrec fun getFactorialResult(number : Int) : Int {

        check(number >= 1, {"잘못된 접근입니다."})

        // TODO 꼬리가 호출이 안된다는 데, 원인 찾아봐야함..
        return when(number) {
            1 -> 1
            else -> number * getFactorialResult(number - 1)
        }
    }

    /**
     * 가변인자 테스트.
     */
    @Test
    fun testVararg() {
        printNumbers(1, 2, 3, 4)
    }

    /**
     * [elements] 에 입력된 데이터를 출력한다.
     *
     * @param elements
     */
    private fun printNumbers(vararg elements : Int) {
        for (i in 0 until elements.size) {
            println(elements[i])
        }
    }

    /**
     * 연산자 오버로딩 테스트.
     */
    @Test
    fun testOperatorOverloading() {
        // 1,2 가 추가된 아이템.
        val items1 = OperatorSupportList<Int>().apply {
            add(1)
            add(2)
        }

        // 3,4 가 추가된 아이템.
        val items2 = OperatorSupportList<Int>().apply {
            add(3)
            add(4)
        }

        val items3 = items1 + items2
        println(items3)

        // 변경 불가한 목록에만 사용가능.
        items3 += items1
        println(items3)
    }

    /**
     * 인라인 함수 테스트.
     *
     * 고차 함수 사용 시, 패널티에 대한 해소로 inline 함수 적용.
     *
     * 고차함수 (람다 등)은 객체로 만들어지면서 함수의 내용을 캡처한다.
     * -> inline 사용 시, 컴파일러가 고차함수 객체를 만들지 않고 호출하는 시점에 코드를 복사하는 방식으로 처리됨.
     */
    @Test
    fun testInlineFunction() {
        // 목록 정의.
        val items = mutableListOf(5, 2, 7, 9, 1)

        // inline function test
        sort(items, { a, b -> a.compareTo(b) })
        println(items)

        // no inline function test
        printSimulateWithNoInlines({str -> println("inline func -> [$str]")}, {str -> println("no inline func -> [$str]")})
    }

    /**
     * 파라미터로 입력받은 [items] 를 [comparator] 에 따라 정렬하는 함수 정의
     *
     * @param items
     * @param comparator
     */
    private inline fun <T> sort(items: MutableList<T>, comparator : (a : T, b : T) -> Int) {
        val size = items.size

        for (i in (1 .. size).reversed()) {
            for (j in 1 until i) {
                val targetA = items[j - 1]
                val targetB = items[j]

                if (comparator(targetA, targetB) > 0) {
                    items[j - 1] = targetB
                    items[j] = targetA
                }
            }
        }
    }

    /**
     * 문자를 특정방식으로 출력하는 프린터 함수 제작.
     *
     * [printer1] 함수는 inline 논리에 따라 컴파일러가 처리
     *
     * inline 으로 된 함수에서 특정 부분을 inline 처리하고 싶지 않다면 [printer2] 처럼 noinline keyword 사용.
     *
     * @param printer1
     * @param printer2
     */
    private inline fun printSimulateWithNoInlines(printer1 : (str :String) -> Unit,  noinline printer2 : (str: String) -> Unit) {
        printer1("Hello, Kotlin")
        printer2("Hello, inline Kotlin")
    }
}