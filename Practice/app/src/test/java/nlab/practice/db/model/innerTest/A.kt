package nlab.practice.db.model.innerTest

/**
 * inner 클래스 테스트.
 *
 * @author ndh1002
 */
class A {
    val nativeAValue = "Naive A Value"

    /**
     * A 의 내부 클래스 정의.
     *
     * 자바에서의 정의는 (static class B) 로, A 클래스에 대한 인스턴스와는 분리되어 있음.
     * [부모 클래스 명(A)] 이란 패키지에 있다고 생각해도 무방.
     *
     * @author ndh1002
     */
    class B {
        /**
         * nativeAValue 에 대한 출력 시도.
         *
         * 그러나 A 클래스에 대한 인스턴스와 분리되었기 때문에, nativeAValue 에는 접근이 불가.
         */
        fun printNativeAValue() {
            // nativeAValue 에는 접근이 불가.
            // println(nativeAValue)
        }
    }

    /**
     * A 의 내부 클래스 정의.
     *
     * 자바에서의 정의는 (class C) 로, A 클래스에 대한 인스턴스와 연결되어있음.
     *
     * @author ndh1002
     */
    inner class C {

        /**
         * nativeAValue 에 대한 출력 시도.
         *
         * 그러나 A 클래스에 대한 인스턴스와 연결되었기 때문에, nativeAValue 에는 접근 가능.
         */
        fun printNativeAValue() {
            println(nativeAValue)
        }
    }
}