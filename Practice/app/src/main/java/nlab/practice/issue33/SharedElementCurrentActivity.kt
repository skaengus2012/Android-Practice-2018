package nlab.practice.issue33

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import kotlinx.android.synthetic.main.activity_shared_element_current.*
import nlab.practice.R

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

            val p1 : android.support.v4.util.Pair<View, String> = android.support.v4.util.Pair.create(ivProfile, "profile")
            val p2 : android.support.v4.util.Pair<View, String> = android.support.v4.util.Pair(tvText, "text")

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(SharedElementCurrentActivity@this, p1, p2)

            startActivity(intent, options.toBundle())
        }
    }
}
