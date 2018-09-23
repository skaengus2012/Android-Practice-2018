package nlab.practice.dagger.model

import javax.inject.Inject

/**
 * Heater 를 주입받아야하는 CoffeeBean 확장 클래스 정의
 *
 * @author Doohyun
 */
class InjectableCoffeeBean @Inject constructor() : CoffeeBean() {

    override fun getName(): String = "Hot CoffeeBean"
}