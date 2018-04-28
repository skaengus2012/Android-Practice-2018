package nlab.practice.issue15

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_view_stub_tutorial.*
import nlab.practice.R
import nlab.practice.util.resource.showToast
import java.lang.ref.WeakReference

/**
 * ViewSub 을 테스트하는 화면 정의.
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/15
 *
 * @author ndh1002
 */
class ViewStubTutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_stub_tutorial)

        ViewStubInflateHandler(this).executeInflate()
    }

    /**
     * ViewStub Inflate 수행.
     */
    private fun inflateViewStub() {
        val view = viewStub.inflate()

        showToast("ViewStub 에 부착된 클래스 -> ${view::class.java.simpleName}")
    }

    /**
     * ViewStubTutorialActivity 가 사용하는 핸들러 정의.
     */
    class ViewStubInflateHandler(activity : ViewStubTutorialActivity) : Handler() {
        private val activityWeakRef = WeakReference(activity)

        /**
         * ViewStub Inflate 수행.
         */
        fun executeInflate() {
            postDelayed({activityWeakRef.get()?.inflateViewStub()}, 1000)
        }
    }
}
