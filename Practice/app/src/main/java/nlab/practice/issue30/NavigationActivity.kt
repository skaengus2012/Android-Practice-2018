package nlab.practice.issue30

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_navigation.*
import nlab.practice.R
import nlab.practice.util.view.NavigationController

/**
 * Single Activity - Multi fragment 를 위한 Navigation
 *
 * @author Doohyun
 */
class NavigationActivity : AppCompatActivity() {

    val navigationController : NavigationController by lazy {
        NavigationController(R.id.layoutFragment, supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        initView()
    }

    /**
     * 뷰 초기화
     */
    private fun initView() {
        navigationView.setOnNavigationItemSelectedListener {

            when(it.itemId) {
                R.id.menu_user -> navigationController.goToUser()
                R.id.menu_note -> navigationController.goToNote()
                R.id.menu_share -> navigationController.goToShare()
            }

            true
        }

        navigationView.selectedItemId = R.id.menu_user
    }
}
