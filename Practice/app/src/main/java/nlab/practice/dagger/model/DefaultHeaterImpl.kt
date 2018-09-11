package nlab.practice.dagger.model

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Heater 의 Default 구현체
 *
 * @author Doohyun
 */
// Pump 와 CoffeeMaker 가 같은 인스턴스를 바라보게 하기 위해 다음과 같이 처리.
// 하지만 상태를 가지는 VO 를 Singleton 화 하면 문제가 있음...
@Singleton
class DefaultHeaterImpl @Inject constructor(): Heater {

    private var _heating : Boolean = false

    override fun on() {
        println("DefaultHeaterImpl ~ ~ heating ~ ~ ~")
        _heating = true
    }

    override fun off() {
        _heating = false
    }

    override fun isHot(): Boolean = _heating
}