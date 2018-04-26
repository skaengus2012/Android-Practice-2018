package nlab.practice.issue11

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import nlab.practice.R

/**
 * 커스텀뷰 튜토리얼을 위한 화면 정의
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/11
 *
 * @author ndh1002
 */
class CustomViewTutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view_tutorial)
    }
}
