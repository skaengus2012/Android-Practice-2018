package nlab.practice.issue14

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import nlab.practice.R
import nlab.practice.util.resource.showToast

/**
 * AppBar 튜토리얼에 사용하는 베이스 화면 정의
 *
 * @author ndh1002
 */
abstract class AppBarTutorialBaseActivity : AppCompatActivity() {

    /**
     * layoutRes 정보 정의.
     *
     * @return
     */
    @LayoutRes abstract fun getLayoutRes() : Int

    /**
     * onCreate 시 해야할 행위 정의
     *
     * - hooker 로 정의.
     *
     * @param savedInstanceState
     */
    open fun onCreateBehavior(savedInstanceState: Bundle?){}

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())

        onCreateBehavior(savedInstanceState)
    }

    /**
     * 메뉴 세팅 메소드
     *
     * @param menu
     * @return
     */
    final override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tutorial_menu, menu)

        return true
    }

    /**
     * 메뉴가 눌렸을 때 해야할 행위 정의
     *
     * @param item
     * @return
     */
    final override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {

        R.id.actionFavorite, R.id.actionSubFavorite -> {
            showToast(R.string.message_app_bar_favorite)
            true
        }

        R.id.actionSetting, R.id.actionSubSetting -> {
            showToast(R.string.message_app_bar_setting)
            true
        }

        R.id.actionSelect1, R.id.actionSelect2 -> {
            item.isChecked = !item.isChecked
            true
        }

        else -> super.onOptionsItemSelected(item)
    }
}