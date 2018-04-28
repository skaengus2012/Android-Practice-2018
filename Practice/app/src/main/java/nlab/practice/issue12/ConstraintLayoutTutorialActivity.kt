package nlab.practice.issue12

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.activity_constraint_layout_tutorial.*
import nlab.practice.R
import nlab.practice.util.resource.convertString
import java.lang.ref.WeakReference
import java.util.*

/**
 * ConstraintLayout 을 테스트할 수 있는 화면 정의
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/12
 *
 * @author ndh1002
 */
class ConstraintLayoutTutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout_tutorial)

        ConstraintViewInitAsyncTask(this).execute()
    }

    /**
     * ViewType 에서 존재하는 뷰들의 초기화 작업을 수행하는 비동기 작업.
     *
     * 속도 개선 이슈 풀이 -> RxAndroid 를 사용하지 않은 이유는 처음으로 회귀하자는 의미.
     *
     * @author ndh1002
     */
    class ConstraintViewInitAsyncTask(activity: ConstraintLayoutTutorialActivity)
        : AsyncTask<Unit, Unit, List<LayoutSampleAdapter.Sample>>() {

        private val activityWeakRef = WeakReference(activity)

        /**
         * 수많은 뷰타입들에 대하여, 메인스레드에서 갑자기 생성하면 속도가 너무 느림.
         *
         * 다른 스레드에서 뷰타입을 만들어 처리하도록 함.
         *
         * @param units
         */
        override fun doInBackground(vararg units : Unit): List<LayoutSampleAdapter.Sample> {

            val context = activityWeakRef.get()?.baseContext

            return if (context == null) {
                // 액티비티가 소멸하지 않은 경우.
                Collections.emptyList()
            } else {
                // 남아있는 경우 inflate 수행.

                val layoutInflater = LayoutInflater.from(context)

                SampleTypeManager.getSamples()
                        .map {
                            LayoutSampleAdapter.Sample(
                                    convertString(it.titlesRes)
                                    , layoutInflater.inflate(it.layoutRes, null))
                        }
                        .toList()
            }
        }

        override fun onPostExecute(result: List<LayoutSampleAdapter.Sample>) {
            super.onPostExecute(result)

            activityWeakRef.get()?.run {
                this.lvSamples.adapter = LayoutSampleAdapter(result)
            }

        }


    }
}
