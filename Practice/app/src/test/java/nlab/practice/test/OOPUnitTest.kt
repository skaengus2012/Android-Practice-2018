package nlab.practice.test

import nlab.practice.model.duck.*
import org.junit.Test

/**
 * 객체지향에 대한 테스트 코드 기술
 *
 * 참고 URL ->
 * https://github.com/skaengus2012/Android-Practice-2018/issues/2
 *
 * @author ndh1002
 */
class OOPUnitTest {

    /**
     * 클래스 사용에 대한 기본 활용 정의.
     */
    @Test
    fun testUsingSimpleClass() {
        val mallardDuck = MallardDuck()
        val rubberDuck = RubberDuck()
        val customType1Duck = CustomType1WingDuck("broDuck")
        val customType2Duck = CustomType2WingDuck("Kang")

        runDuckSimulator(mallardDuck)
        runDuckSimulator(rubberDuck)
        runDuckSimulator(customType1Duck)
        runDuckSimulator(customType2Duck)
    }

    /**
     * 오리 시뮬레이터 기능 정의.
     *
     * [duck] 을 이용한 여러 기능 수행..
     *
     * @param duck
     */
    private fun runDuckSimulator(duck : Duck) {
        println()
        println("${duck.javaClass.simpleName} 객체 시뮬레이션.")

        duck.swim()
        duck.quack()
        duck.fly()
        println()
    }

}