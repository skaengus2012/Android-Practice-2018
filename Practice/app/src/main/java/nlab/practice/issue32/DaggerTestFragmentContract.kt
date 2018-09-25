package nlab.practice.issue32

/**
 * @author Doohyun
 */
interface DaggerTestFragmentContract {
    interface View {
        fun bindMessage(message: String)
    }
    interface Presenter {
        fun requestMessage()
        fun clear()
    }
}