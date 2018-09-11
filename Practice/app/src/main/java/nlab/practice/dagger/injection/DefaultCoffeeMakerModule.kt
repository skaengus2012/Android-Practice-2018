package nlab.practice.dagger.injection

import dagger.Module
import dagger.Provides
import nlab.practice.dagger.model.DefaultHeaterImpl
import nlab.practice.dagger.model.DefaultPumpImpl
import nlab.practice.dagger.model.Heater
import nlab.practice.dagger.model.Pump

/**
 * Default 구현체들에 대한 관계를 구성하는 CoffeeMaker Module
 *
 * @author Doohyun
 */
@Module
class DefaultCoffeeMakerModule {

    /**
     * Heater 객체 생성
     */
    @Provides
    fun provideHeater(heater: DefaultHeaterImpl) : Heater = heater

    /**
     * [heater] 객체를 주입받은 Pump 를 생성
     */
    @Provides
    fun providePump(heater: Heater) : Pump = DefaultPumpImpl(heater)
}