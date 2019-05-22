package nlab.practice.test

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.nio.file.Paths
import kotlin.collections.ArrayList

/**
 * Inline function, class 관련 테스트
 *
 * Kotlin in Action 8장의 내용 및 기타 Inline 테스트
 *
 * @author Doohyun
 * @since 2019. 05. 22
 */
class InlineTest {

    @Test
    fun testNoneLocal() {
        val items = listOf(1, 2, 3, 4)

        assertTrue(subListUsingNoneLocal(items, 2) == items.subList(0, 2))
        assertTrue(subListUsingNoneInline(items, 2) == items.subList(0, 2))
    }

    /**
     * [items] 를 넣어, [targetSize] 까지만 데이터를 추가하는 함수 제작
     *
     * foreach 자체가 inline function 이기 때문에 non-local 이 됨
     */
    private fun subListUsingNoneLocal(items: List<Int>, targetSize: Int) : List<Int> {
        val results = ArrayList<Int>()

        items.foreachInline {
            if (results.size == targetSize) {
                return results
            } else {
                results += it
            }
        }

        return results
    }

    private fun subListUsingNoneInline(items: List<Int>, targetSize: Int) : List<Int> {
        val results = ArrayList<Int>()

        items.foreachNoneInline {
            if (results.size == targetSize) {
            //  inline function 이 아닌 경우, 컴파일이 안됨
            //  return results
            } else {
                results += it
            }
        }

        return results
    }

    private inline fun <T> List<T>.foreachInline(action: (T) -> Unit) {
        for (obj in this) {
            action(obj)
        }
    }

    private fun <T> List<T>.foreachNoneInline(action: (T) -> Unit) {
        for (obj in this) {
            action(obj)
        }
    }

    @Test
    fun testLabelReturn() {
        assertFalse(foreachWithLabelReturn())
        assertFalse(foreachWithFunctionLabelReturn())
        assertFalse(foreachWithAnonymousFunction())
    }

    private fun foreachWithLabelReturn(): Boolean {
        listOf(1, 2, 3).foreachInline label@{
            if (it == 2) {
                // 람다 내부의 return
                return@label
            }

            println(it)
        }

        return false
    }

    private fun foreachWithFunctionLabelReturn(): Boolean {
        listOf(1, 2, 3).foreachInline {
            if (it == 2) {
                // 람다 내부의 return
                return@foreachInline
            } else {
                println(it)
            }
        }

        return false
    }

    private fun foreachWithAnonymousFunction(): Boolean {
        listOf(1, 2, 3).foreachInline (fun (number) {
            if (number == 2) {
                return
            } else {
                println(number)
            }
        })

        return false
    }

    /**
     * Kotlin 의 Try-with-resource
     */
    @Test
    fun testResource() {
        val currentFile = "${Paths.get("").toAbsolutePath()}/src/test/java/nlab/practice/test/InlineTest.kt"

        BufferedReader(FileReader(currentFile)).useLines {
            it.forEach { text -> println(text) }
        }
    }

}