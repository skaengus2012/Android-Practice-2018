package nlab.practice.test

import org.junit.Assert
import org.junit.Test

/**
 * 확장 가능한 객체 테스트
 *
 * Kotlin in Action 7장에 나오는 내용
 *
 * @author Doohyun
 * @since 2019. 05. 21
 */
class ExpandableObjectTest {

    @Test
    fun testPerson() {
        val person = Person()
        val data = mapOf("name" to "Doohyun", "company" to "naver")

        for ((key, value) in data) {
            person.setAttribute(key, value)
        }

        person.name.run { Assert.assertTrue(this == "Doohyun") }
    }

}

class Person {

    private val _attribute = hashMapOf<String, String>()

    fun setAttribute(attrName: String, value: String) {
        _attribute[attrName] = value
    }

    val name: String by _attribute
}