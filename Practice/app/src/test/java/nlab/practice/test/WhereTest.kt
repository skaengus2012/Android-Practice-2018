package nlab.practice.test

import org.junit.Test

/**
 * @author Doohyun
 * @since 2019. 06. 05
 */
class WhereTest {

    @Test
    fun testWhere() {
        val aImpl = object : A {}
        val bImpl = object : B {}
        val abImpl = object : A, B {}

      //  something(aImpl) // 컴파일 에러
      //  something(bImpl) // 컴파일 에러
        something(abImpl)

    }

    fun <T> something(t: T) where
            T : A,
            T : B {

    }
}

interface A
interface B