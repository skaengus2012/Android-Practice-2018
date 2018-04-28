package nlab.practice.issue14

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import nlab.practice.R

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
}
