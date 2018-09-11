package nlab.practice.dagger.model

/**
 * Heater 에 대한 인터페이스 정의
 *
 * @author Doohyun
 */
interface Heater {
    fun on()
    fun off()
    fun isHot() : Boolean
}