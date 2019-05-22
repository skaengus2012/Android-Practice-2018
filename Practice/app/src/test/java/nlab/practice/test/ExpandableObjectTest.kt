package nlab.practice.test

import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 확장 가능한 객체 테스트
 *
 * Kotlin in Action 7장
 * 위임 프로퍼티의 기타 예제 (Lazy 와 Delegate.observable 은 대중적이기 때문에 따로 하지 않음)
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

        person.name.run { assertTrue(this == "Doohyun") }
    }

}

class Person {

    private val _attribute = hashMapOf<String, String>()

    fun setAttribute(attrName: String, value: String) {
        _attribute[attrName] = value
    }

    var name: String by _attribute
}