package nlab.practice.issue17

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_custom_tool_bar_tutorial.*
import nlab.practice.R
import nlab.practice.issue14.AppBarTutorialBaseActivity

/**
 * Custom Toolbar 튜토리얼
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/17
 *
 * @author ndh1002
 */
class CustomToolBarTutorialActivity : AppBarTutorialBaseActivity() {

    override fun getLayoutRes(): Int = R.layout.activity_custom_tool_bar_tutorial

    /**
     * toolBar 연결.
     *
     * @param savedInstanceState
     */
    override fun onCreateBehavior(savedInstanceState: Bundle?) {
        setSupportActionBar(myToolBar)

        // 백버튼 추가.
        // 참고 : https://stackoverflow.com/questions/26651602/display-back-arrow-on-toolbar
        supportActionBar?.let {
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    /**
     * 백 버튼 처리
     *
     * @return
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
