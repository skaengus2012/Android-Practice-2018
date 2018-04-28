package nlab.practice.issue14

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import nlab.practice.R
import nlab.practice.util.resource.showToast

/**
 * 앱 바 및 메뉴 예제 화면 정의
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/14
 *
 * @author ndh1002
 */
class AppBarTutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_bar_tutorial)
    }

    /**
     * 메뉴 세팅 메소드
     *
     * @param menu
     * @return
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tutorial_menu, menu)

        return true
    }

    /**
     * 메뉴가 눌렸을 때 해야할 행위 정의
     *
     * @param item
     * @return
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {

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
