package nlab.practice.db.model.duck

import nlab.practice.db.model.strategy.FlyWithWing

/**
 * 청둥오리 클래스 정의.
 *
 * @author ndh1002
 */
class MallardDuck : Duck() {

    /**
     * init 키워드를 이용하여, 초기화 가능.
     *
     * -> 초기화 작업이 필요하다면, 이 키워드를 이용해보자.
     */
    init {
        this.flyable = FlyWithWing()
    }

    override fun quack() = println("청둥오리가 크게 울기 시작했다.")

    override fun swim() =  println("청둥오리가 천둥과 같은 속도로 헤엄을 치기 시작했다.")
}