package nlab.practice.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import nlab.practice.R

/**
 * 개발자 튜토리얼 화면 정의
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/8
 *
 * @author ndh1002
 */
class DevTutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dev_tutorial)
    }
}
