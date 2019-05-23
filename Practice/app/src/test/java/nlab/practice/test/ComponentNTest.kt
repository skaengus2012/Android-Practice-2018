package nlab.practice.test

import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 구조 분해 선언에 대한 예제 테스트
 *
 * Kotlin in Action 7장
 *
 * @author Doohyun
 * @since 2019. 05. 22
 */
class ComponentNTest {

    @Test
    fun testSimpleComponentN() {
        val point = Point(10, 20)

        val (x, y) = point

        assertTrue(x == 10)
        assertTrue(y == 20)
    }

    @Test
    fun testSplitFileName() {
        val targetFile = "Hello.kt"

        val (fileName, extension) = splitFileName(targetFile)

        assertTrue(fileName == "Hello")
        assertTrue(extension == "kt")
    }
}

/**
 * NOTE : data class 의 경우 구조분해 선언을 최대 5개까지 지
 */
data class Point(var x: Int, var y: Int)

data class NameComponent(val name: String, val extension: String)

fun splitFileName(fullName: String): NameComponent = fullName.split(".").let {
    NameComponent(it[0], it[1])
}
