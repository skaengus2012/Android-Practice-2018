package nlab.practice.model.duck

/**
 * 날 수 있는 이름 정의 가능 오리 클래스 정의.
 *
 * init 키워드를 이용한 생성자 생성.
 * 파라미터 직접 전달 방식.
 *
 * @author ndh1002
 */
class CustomType2WingDuck(name: String) : NamedDuck(name) {

    init {
        this.flyable = FlyWithWing()
    }
}