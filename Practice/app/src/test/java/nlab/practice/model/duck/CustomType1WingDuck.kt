package nlab.practice.model.duck

import nlab.practice.model.strategy.FlyWithWing

/**
 * 날 수 있는 이름 정의 가능 오리 클래스 정의.
 *
 * constructor 키워드를 이용한 생성자 생성.
 *
 * @author ndh1002
 */
class CustomType1WingDuck : NamedDuck {

    // constructor 키워드를 이용하여, 생성자를 정의할 수 있음.
    constructor(name: String) : super(name) {
        flyable = FlyWithWing()
    }
}