package nlab.practice.test

import nlab.practice.dagger.component.DaggerCafeComponent
import nlab.practice.dagger.component.DaggerParentCafeComponent
import org.junit.Test

/**
 * 조금더 심화된 Dagger Test Class 정의
 *
 * inject, Module, Component  주요 모듈 사용
 *
 * 참고 URL
 * @see { https://cmcmcmcm.blog/2017/08/02/didependency-injection-와-dagger2-2 }
 * @author Doohyun
 */
class DaggerChapter2Test {

    /**
     * 단일 CafeComponent 에서 생성되는 객체 범위 테스트
     */
    @Test
    fun testSingleComponentScope() {
        val cafeComponent = DaggerCafeComponent.create()

        val cafeInfo1 = cafeComponent.cafeInfo()
        val cafeInfo2 = cafeComponent.cafeInfo()
        println("cafeInfo1 과 cafeInfo2는 같은가? -> ${cafeInfo1 === cafeInfo2}")

        val coffeeMaker1 = cafeComponent.maker()
        val coffeeMaker2 = cafeComponent.maker()
        println("coffeeMaker1 과 coffeeMaker2 는 같은가? -> ${coffeeMaker1 === coffeeMaker2}")
    }

    /**
     * SubComponent 들에 대한 객체 범위 테스트
     */
    @Test
    fun testSubComponentScope() {
        val cafeComponent = DaggerParentCafeComponent.create()

        val coffeeComponent1 = cafeComponent.coffeeComponent().build()
        val coffeeComponent2 = cafeComponent.coffeeComponent().build()

        // 스코프를 가진 같은 컴포넌트에서 생성된 데이터는 동일한 것이 등장
        val coffeeMaker1 = coffeeComponent1.maker()
        val coffeeMaker2 = coffeeComponent1.maker()
        println("coffeeMaker1 과 coffeeMaker2 는 같은가? -> ${coffeeMaker1 === coffeeMaker2}")

        // 스코프를 가진 다른 컴포넌트에서 생성된 데이터는 다름
        val coffeeMaker3 = coffeeComponent2.maker()
        println("coffeeMaker1 과 coffeeMaker3 는 같은가? -> ${coffeeMaker1 === coffeeMaker3}")

        // 스코프를 가지지 않는다면, 다른 객체가 생성됨
        val coffeeBean1 = coffeeComponent1.coffeeBeen()
        val coffeeBean2 = coffeeComponent1.coffeeBeen()
        println("coffeeBean1 과 coffeeBean2 는 같은가? -> ${coffeeBean2 === coffeeBean1}")
    }

    /**
     * Bind Annotation 에 의해 주입된 객체를 제공하는 기능 테스트
     */
    @Test
    fun testBinds() {
        val coffeeBeanGroup = DaggerCafeComponent.create().coffeeBeanGroup()
        coffeeBeanGroup.values.forEach { println(it.getName()) }
    }

}