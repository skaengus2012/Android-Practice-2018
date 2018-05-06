package nlab.practice.db.model.strategy

/**
 * 날개를 이용하여, 하늘을 나는 행위 정의.
 *
 * @author ndh1002
 */
class FlyWithWing : Flyable {
    override fun fly() = println("날개를 이용하는 수직 상승~")
}