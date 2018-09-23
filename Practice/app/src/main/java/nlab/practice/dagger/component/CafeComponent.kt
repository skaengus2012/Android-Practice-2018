package nlab.practice.dagger.component

import dagger.Component
import nlab.practice.dagger.module.CafeModule
import nlab.practice.dagger.model.CafeInfo
import nlab.practice.dagger.model.CoffeeBean
import nlab.practice.dagger.model.CoffeeMaker
import nlab.practice.dagger.module.BindCoffeeModule
import javax.inject.Singleton

/**
 * Cafe 정보 구성을 위한 컴포넌트 정
 *
 * @author Doohyun
 */
@Singleton
@Component(modules = [CafeModule::class, BindCoffeeModule::class])
interface CafeComponent {
    fun cafeInfo() : CafeInfo
    fun maker() : CoffeeMaker
    fun coffeeBeanGroup() : Map<String, CoffeeBean>
}