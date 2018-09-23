package nlab.practice.dagger.component

import dagger.Subcomponent
import nlab.practice.dagger.scope.CoffeeScope
import nlab.practice.dagger.module.CoffeeModule
import nlab.practice.dagger.model.CoffeeBean
import nlab.practice.dagger.model.CoffeeMaker

/**
 * @author Doohyun
 */
@CoffeeScope
@Subcomponent(modules = [CoffeeModule::class])
interface CoffeeComponent {

    fun maker() : CoffeeMaker

    fun coffeeBeen() : CoffeeBean

    @Subcomponent.Builder
    interface Builder {
        fun build() : CoffeeComponent
    }
}