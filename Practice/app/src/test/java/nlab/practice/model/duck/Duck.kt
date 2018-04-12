package nlab.practice.model.duck

/**
 * 기본이 되는 오리 클래스 정의.
 *
 * @author ndh1002
 */
abstract class Duck {

    protected var flyable : Flyable? = null

    fun fly() = flyable?.fly()

    /**
     * 오리가 우는 행위 구현.
     *
     * 참고. 접근제한자에 대해 PUBLIC
     */
    abstract fun quack()

    /**
     * 수영을 하는 행위 구현.
     */
    abstract fun swim()
}