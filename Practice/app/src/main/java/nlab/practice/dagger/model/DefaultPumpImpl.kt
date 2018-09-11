package nlab.practice.dagger.model

import javax.inject.Inject

/**
 * Default Pump 구현체
 *
 * @author Doohyun
 */
class DefaultPumpImpl @Inject constructor(private var _heater: Heater) : Pump {

    override fun pump() {
        if (_heater.isHot()) {
            println("DefaultPump > pumping => =>")
        }
    }
}