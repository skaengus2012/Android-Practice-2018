package nlab.practice.dagger.component

import dagger.Component
import nlab.practice.dagger.model.CafeInfo
import nlab.practice.dagger.module.ParentCafeModule
import javax.inject.Singleton

/**
 * @author Doohyun
 */
@Singleton
@Component(modules = [ParentCafeModule::class])
interface ParentCafeComponent {
    fun cafeInfo() : CafeInfo
    fun coffeeComponent() : CoffeeComponent.Builder
}