package nlab.practice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import nlab.practice.main.MainItemListAdapter
import nlab.practice.main.MainItemListAdapter.MainItem
import nlab.practice.ui.DevTutorialActivity

/**
 * 앱 테스트를 위한 인트로 화면 정의
 *
 * @author ndh1002
 */
class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainItemListAdapter(getTestComponentMainItems()).let { lvLabels.adapter = it }
    }

    /**
     * 테스트에 필요한 컴포넌트 정보를 담은 목록 출력.
     *
     * @return
     */
    private fun getTestComponentMainItems() : List<MainItem> = listOf(
            MainItem(R.string.title_dev_tutorial, DevTutorialActivity::class.java)
    )
}
