package nlab.practice.dagger.module

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import nlab.practice.dagger.model.AdvancedCoffeeBean
import nlab.practice.dagger.model.CoffeeBean
import nlab.practice.dagger.model.InjectableCoffeeBean

/**
 * Bind 관련 모듈에 대한 테스트
 *
 * Binds : 이미 Provide 하거나 주입대상 객체에 대해 메소드 서명만 만들면 제공을 대신 해줌
 * IntoMap : 여러 동일 제공자가 존재할 때, MultiBinding 을 하여 특정 자료구조로 내보낼 수 있음
 *
 * @author Doohyun
 */
@Module
abstract class BindCoffeeModule {

    @Binds
    @IntoMap
    @StringKey(value = "injectable")
    abstract fun provideInjectableCoffeeBean(coffeeBean: InjectableCoffeeBean) : CoffeeBean

    // FIXME provide 는 멀티바인딩이 안되는듯
    @Binds
    @IntoMap
    @StringKey(value = "Advanced")
    abstract fun provideAdvancedCoffeeBean(coffeeBean: AdvancedCoffeeBean) : CoffeeBean
}