package nlab.practice.model.duck

import nlab.practice.model.strategy.Heat
import nlab.practice.model.strategy.Runnable

/**
 * 이름을 가지는 오리 클래스 정의.
 *
 * 추상클래스가 아닌 클래스를 상속받으려면, open 키워드가 앞에 붙어야함. ->
 * 코틀린의 클래스는 기본적으로 final
 *
 * @author ndh1002
 */
open class NamedDuck(name : String) : Duck() {

    // 코틀린에서는 기본적으로 변수/상수에 대해 접근제한을 사용하지 않음. (PUBLIC)
    var name : String = name
    var heat : Heat? = null
    var runnable : Runnable? = null

    override fun quack() = println("$name 이(가) 울기 시작.")

    override fun swim() = println("$name 이(가) 수영을 시작.")

    /**
     * 의존성을 주입받아, 실행하는 행위에 대한 구현.
     */
    fun executeDIBehavior() {
        println("\n$name 차례")

        fly()
        runnable?.run()
        heat?.attack()

        println()
    }
}