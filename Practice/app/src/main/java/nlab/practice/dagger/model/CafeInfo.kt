package nlab.practice.dagger.model

/**
 * @author Doohyun
 */
class CafeInfo {

    private var _name: String? = null

    constructor()

    constructor(name: String) {
        _name = name
    }

    fun welcome() {
        println("Welcome ${_name ?: ""}")
    }
}