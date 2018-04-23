package nlab.practice.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_display_message.*
import nlab.practice.R

/**
 * 개발자 튜토리얼 - 표시 화면에 대한 정의
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/8
 *
 * @author ndh1002
 */
class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        textView.text = intent.extras.getString(DevTutorialActivity.EXTRA_MESSAGE)
    }
}
