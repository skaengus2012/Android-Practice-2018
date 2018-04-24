package nlab.practice.c3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import nlab.practice.R

/**
 * 프래그먼트 교체 연습 화면 정의
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/20
 *
 * @author ndh1002
 */
class FragmentChangeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_change)
    }
}
