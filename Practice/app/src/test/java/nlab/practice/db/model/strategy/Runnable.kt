package nlab.practice.db.model.strategy

/**
 * 봉합 클래스 정의.
 *
 * 일련의 클래스를 한 묶음으로 제작.
 *
 * @author ndh1002
 */
sealed class Runnable {

    /**
     * Runnable 의 클래스는 해당 메소드를 모두 구현해야함.
     */
    abstract fun run()

    /**
     * 매우 빠른 속도 정의
     *
     * @author ndh1002
     */
    class High : Runnable() {
        override fun run() = println("매우 빠른 속도로 달리기")
    }

    /**
     * 기본 속도 정의
     *
     * @author ndh1002
     */
    class Normal : Runnable() {
        override fun run() = println("기본 속도로 달리기")
    }

    /**
     * 느린 속도 정의
     *
     * @author ndh1002
     */
    class Low : Runnable() {
        override fun run() = println("느린 속도로 달리기")
    }
}