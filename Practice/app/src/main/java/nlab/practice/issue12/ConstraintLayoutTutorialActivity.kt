package nlab.practice.issue12

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_constraint_layout_tutorial.*
import nlab.practice.R

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

        lvSamples.adapter = LayoutSampleAdapter()
    }
}
