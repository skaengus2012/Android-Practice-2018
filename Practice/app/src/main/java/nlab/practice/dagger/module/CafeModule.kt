package nlab.practice.dagger.module

import dagger.Module
import dagger.Provides
import nlab.practice.dagger.model.CafeInfo
import nlab.practice.dagger.model.CoffeeMaker
import nlab.practice.dagger.model.DefaultHeaterImpl
import nlab.practice.dagger.model.Heater
import javax.inject.Singleton

/**
 * Cafe 정보를 구성하기 위한 모듈 정의
 *
 * @author Doohyun
 */
@Module
class CafeModule {

    @Singleton
    @Provides
    fun provideCafeInfo() : CafeInfo = CafeInfo()

    @Provides
    fun provideHeater() : Heater = DefaultHeaterImpl()

    @Provides
    fun provideCoffeeMaker(heater: Heater) : CoffeeMaker = CoffeeMaker().apply { this.heater = heater }
}