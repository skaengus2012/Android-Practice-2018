package nlab.practice.issue39

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import nlab.practice.R
import org.jetbrains.anko.*

/**
 * Anko 로 그린 UI
 *
 * @author Doohyun
 */
class AnkoActivityUI : AnkoComponent<AnkoTestActivity> {
    override fun createView(ui: AnkoContext<AnkoTestActivity>): View =
        ui.apply {
            verticalLayout {
                // 앱 바 영역
                linearLayout {
                    backgroundColor = Color.parseColor("#efeeef")
                    gravity = Gravity.CENTER
                    orientation = LinearLayout.HORIZONTAL

                    lparams(width = matchParent, height = dimen(R.dimen.google_play_app_bar_size))

                    imageView(R.drawable.ic_google_play).lparams(width = dip(130), height = dip(30))

                    editText {
                        leftPadding = dip(5)
                        rightPadding = dip(5)
                        bottomPadding = dip(0)
                        topPadding = dip(0)
                        backgroundColorResource = android.R.color.white
                        hintResource = R.string.hint_anko_search
                        textSize = 13f // xml 의 13sp 와 동일
                        singleLine = true
                    }.lparams( weight = 1f, width = dip(50), height = dip(35))

                    button (R.string.label_anko_search) {
                        backgroundColor = Color.parseColor("#5383ED")
                        textColorResource = android.R.color.white
                        textSize = 13f
                        leftPadding = dip(10)
                        rightPadding = dip(10)
                        minWidth = dip(0)
                        minimumWidth = dip(0)
                    }.lparams (width = wrapContent, height = dip(35)) {
                        rightMargin = dip(5)
                    }

                }
            }
        }.view

}