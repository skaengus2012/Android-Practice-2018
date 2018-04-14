package nlab.practice.test

import org.junit.Test

/**
 * Kotlin keyword 관련 테스트 슈트 정의
 *
 * 참고 URL
 *  -> https://github.com/skaengus2012/Android-Practice-2018/issues/3
 *
 * @author ndh1002
 */
class KeywordUnitTest {

    /**
     * Type Casting 관련 테스트 제작.
     */
    @Test
    fun testTypeCast() {
        // Any Type 은 모든 타입의 최상위를 의미. -> 자바의 Object 와 유사.
        val number : Any = 5.5

        // as 키워드를 사용하여, 캐스팅을 할 수 있음.
        // 타입 변환 실패 시, 예외를 던짐. (Unsafe Type Cast)
        val numberDouble = number as Double
        println(numberDouble)

        // as? 키워드 역시, 타입 캐스팅. (Optional 현식으로 타입 캐스팅).
        // 타입 변환 실패 시, 없는 상태의 Optional 로 처리 (safe type Cast)
        val numberInt = numberDouble as? Int
        numberInt?.let { println(it) }
    }
}