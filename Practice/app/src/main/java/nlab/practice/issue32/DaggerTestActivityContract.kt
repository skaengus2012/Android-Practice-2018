package nlab.practice.issue32

/**
 * MVP 에서 프리젠터 정보 정의
 *
 * @author Doohyun
 */
interface DaggerTestActivityContract {

    interface View {
        /**
         * [description] 정보를 뷰에 바인딩
         */
        fun setMainDescription(description: String)
    }

    interface Presenter {
        /**
         * Main 정보를 요청
         */
        fun requestMainDescription()

        /**
         * Rx 동작에 대한 종료
         */
        fun clear()
    }
}