package nlab.practice.dagger.model

import javax.inject.Inject

/**
 * CoffeeBean 의 기본 확장체
 *
 * @author Doohyun
 */
class AdvancedCoffeeBean @Inject constructor(): CoffeeBean(){
    override fun getName(): String = "Advanced CoffeeBean"
}