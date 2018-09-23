package nlab.practice.dagger.module

import dagger.Module
import dagger.Provides
import nlab.practice.dagger.model.CoffeeBean
import nlab.practice.dagger.model.CoffeeMaker
import nlab.practice.dagger.model.DefaultHeaterImpl
import nlab.practice.dagger.model.Heater
import nlab.practice.dagger.scope.CoffeeScope

/**
 * Cafe 의 하위컴포넌트 지원을 위한 모듈
 *
 * @author Doohyun
 */
@Module
class CoffeeModule {

    @CoffeeScope
    @Provides
    fun provideHeater() : Heater = DefaultHeaterImpl()

    @CoffeeScope
    @Provides
    fun provideCoffeeMaker(heater: Heater) : CoffeeMaker = CoffeeMaker().apply { this.heater  = heater }

    @Provides
    fun provideCoffeeBean() : CoffeeBean = CoffeeBean()
}