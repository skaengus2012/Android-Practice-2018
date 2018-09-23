package nlab.practice.test

import nlab.practice.dagger.component.DaggerCoffeeMakerComponent
import nlab.practice.dagger.provideCoffeeMaker
import nlab.practice.dagger.model.CoffeeMaker
import nlab.practice.dagger.model.DefaultHeaterImpl
import nlab.practice.dagger.model.DefaultPumpImpl
import org.junit.Test

/**
 * 기본적인 Dagger Test Class 정의
 *
 * inject, Module, Component  주요 모듈 사용
 *
 * 참고 URL
 * @see { https://cmcmcmcm.blog/2017/07/27/didependency-injection-와-dagger2/ }
 *
 * @author Doohyun
 * @since 2018. 09. 21
 */
class DaggerChapter1Test {

    /**
     * DI 를 활용하지 않은 심플 테스트
     */
    @Test
    fun testSimple() {
        val heater = DefaultHeaterImpl()
        var pump = DefaultPumpImpl(heater)
        var coffeeMaker = CoffeeMaker(heater, pump)

        coffeeMaker.brew()
    }

    /**
     * 객체 생성을 공급화 (팩토리)한 메소드를 이용하여 테스트
     */
    @Test
    fun testInjectionUtil() {
        val coffeeMaker = provideCoffeeMaker()
        coffeeMaker.brew()
    }

    /**
     * Dagger 를 이용한 DI 처리
     */
    @Test
    fun testDaggerInjection1() {
        DaggerCoffeeMakerComponent.create().maker().brew()
    }

    /**
     * Dagger 를 이용한 DI 처리
     */
    @Test
    fun testDaggerInjection2() {
        val coffeeMaker = CoffeeMaker()
                .apply { DaggerCoffeeMakerComponent.create().inject(this) }

        coffeeMaker.brew()
    }
}