package nlab.practice.dagger.model

/**
 * Heater 의 Default 구현체
 *
 * @author Doohyun
 */
class DefaultHeaterImpl: Heater {

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