package nlab.practice.issue33.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import kotlinx.android.synthetic.main.activity_shared_element_current.*
import nlab.practice.R
import nlab.practice.util.GlideApp
import nlab.practice.util.V4Pair

private const val IMAGE_URL = "http://mblogthumb1.phinf.naver.net/20160203_20/papero2_1454475327245xUJXD_JPEG/%BA%ED%B7%A2%C0%A7%B5%B5%BF%EC.jpg?type=w2"

/**
 * SharedTargetElement 를 부를 화면
 *
 * @author Doohyun
 */
class SharedElementCurrentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_element_current)

        layoutCurrent.setOnClickListener {
            val intent = Intent(this, SharedElementTargetActivity::class.java)

            val p1 : V4Pair<View, String> = V4Pair.create(ivProfile, "profile")
            val p2 : V4Pair<View, String> = V4Pair.create(tvText, "text")

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(SharedElementCurrentActivity@this, p1, p2)

            startActivity(intent, options.toBundle())
        }

        GlideApp.with(this)
                .load(IMAGE_URL)
                .dontAnimate()
                .into(ivProfile)
    }
}
