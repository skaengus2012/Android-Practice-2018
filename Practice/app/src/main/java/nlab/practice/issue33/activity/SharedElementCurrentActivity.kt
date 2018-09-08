package nlab.practice.issue33.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import kotlinx.android.synthetic.main.activity_shared_element_current.*
import nlab.practice.R
import nlab.practice.util.GlideApp
import nlab.practice.util.V4Pair

const val SHARED_ELEMENT_ACTIVITY_IMAGE_URL = "https://newsbang.com/resources/2017/03/08/30304216649680536305147929.gif"

/**
 * SharedTargetElement 를 부를 화면
 *
 * @author Doohyun
 */
class SharedElementCurrentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_element_current)

        btnNextPage.setOnClickListener {
            val intent = Intent(this, SharedElementTargetActivity::class.java)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    SharedElementCurrentActivity@ this,
                    V4Pair.create(ivProfile, ViewCompat.getTransitionName(ivProfile)))

            startActivity(intent, options.toBundle())
        }

        GlideApp.with(this)
                .load(SHARED_ELEMENT_ACTIVITY_IMAGE_URL)
                .dontAnimate()
                .into(ivProfile)
    }
}
