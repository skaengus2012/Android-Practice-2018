package nlab.practice.test

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

        return when(number) {
            1 -> 1
            else -> number * getFactorialResult(number - 1)
        }
    }

}