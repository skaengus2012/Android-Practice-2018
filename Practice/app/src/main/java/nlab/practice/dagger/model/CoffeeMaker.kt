package nlab.practice.dagger.model

import javax.inject.Inject

/**
 * Heater 와 Pump 에 대해 의존적인 Coffee Maker
 *
 * @author Doohyun
 */
class CoffeeMaker {

    // inject 를 필드에 하는 경우, private 이면 안됨
    @Inject
    lateinit var heater: Heater

    @Inject
    lateinit var pump: Pump

    /**
     * CoffeeMakerComponent::inject 사용을 위해 임시로 만듬
     */
    constructor()

    /**
     * CoffeeMakerComponent::maker 에 의해 불리며, 자동으로 [heater] 와 [pump] 가 주입됨
     */
    @Inject constructor(heater: Heater, pump: Pump) {
        this.heater = heater
        this.pump = pump
    }

    fun brew() {
        heater.on()
        pump.pump()
        println(" [_]P coffee! [_]P ")
        heater.off()
    }
}