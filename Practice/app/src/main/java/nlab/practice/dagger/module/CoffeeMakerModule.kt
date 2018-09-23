package nlab.practice.dagger.module

import dagger.Module
import dagger.Provides
import nlab.practice.dagger.model.*
import javax.inject.Singleton

/**
 * Default 구현체들에 대한 관계를 구성하는 CoffeeMaker Module
 *
 * @author Doohyun
 */
@Module
class CoffeeMakerModule {

    /**
     * Heater 객체 생성
     *
     * Pump 와 CoffeeMaker 가 같은 인스턴스를 바라보게 하기 위해 다음과 같이 처리.
     * 하지만 상태를 가지는 VO 를 Singleton 화 하면 문제가 있음...
     */
    @Singleton
    @Provides
    fun provideHeater() : Heater = DefaultHeaterImpl()

    /**
     * [heater] 객체를 주입받은 Pump 를 생성
     */
    @Provides
    fun providePump(heater: Heater) : Pump = DefaultPumpImpl(heater)
}