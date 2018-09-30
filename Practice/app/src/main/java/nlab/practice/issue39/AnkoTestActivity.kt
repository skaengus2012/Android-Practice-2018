package nlab.practice.issue39

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.LinearLayout
import nlab.practice.R
import nlab.practice.util.resource.convertColor
import org.jetbrains.anko.*

/**
 * Anko 로 UI 구성하도록 정의된 화면 정의
 *
 * @author Doohyun
 */
class AnkoTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AnkoActivityUI().setContentView(this)

    }
}