package nlab.practice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import nlab.practice.main.MainItemListAdapter
import nlab.practice.main.MainItemListAdapter.MainItem
import nlab.practice.ui.DevTutorialActivity
import nlab.practice.util.convertString

/**
 * 앱 테스트를 위한 인트로 화면 정의
 *
 * @author ndh1002
 */
class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainItemListAdapter(
                MainItem(convertString(R.string.title_dev_tutorial), DevTutorialActivity::class.java)
        ).let {
            lvLabels.adapter = it
        }
    }
}
