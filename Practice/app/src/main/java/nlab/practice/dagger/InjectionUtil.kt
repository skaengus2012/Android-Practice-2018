package nlab.practice.dagger

import nlab.practice.dagger.model.*

/**
 * Injection 함수 정의
 *
 * @author Doohyun
 */

/**
 * Heater 객체를 공급
 */
fun provideHeater() : Heater = DefaultHeaterImpl()

/**
 * Pump 객체를 공급
 */
fun providePump(heater: Heater) : Pump = DefaultPumpImpl(heater)

/**
 * CoffeeMaker 객체를 공급
 */
fun provideCoffeeMaker() : CoffeeMaker {
    val heater = provideHeater()

    return CoffeeMaker(heater, providePump(heater))
}
