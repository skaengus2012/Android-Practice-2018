package nlab.practice.issue16

import android.graphics.Outline
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import android.view.ViewOutlineProvider
import kotlinx.android.synthetic.main.activity_shadow_and_clipping.*
import nlab.practice.R

/**
 * 그림자 & 클리핑 튜토리얼 화면.
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/16
 *
 * @author ndh1002
 */
class ShadowClippingActivity : AppCompatActivity() {

    private var isClipping = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shadow_and_clipping)

        // 아웃라인 프로바이더 세팅.
        if (isTargetLollipopUpper()) {
            viewTargetClipping.outlineProvider = createViewOutlineProvider()
        }

        btnToggleClipping.setOnClickListener({
            if (isTargetLollipopUpper()) {
                toggleClipping()
            }
        })
    }

    /**
     * 롤리팝 이상의 버전코드 체크
     *
     * @return
     */
    private fun isTargetLollipopUpper() : Boolean = android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

    /**
     * [view] 에 대하여 클리핑 토글링 처리
     *
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun toggleClipping() {
        isClipping = !isClipping
        viewTargetClipping.clipToOutline = isClipping
    }

    /**
     * 원형으로 뷰를 깎는 ViewOutlineProvider 를 생산.
     *
     * 참고 정보 -> https://github.com/googlesamples/android-ClippingBasic
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun createViewOutlineProvider() : ViewOutlineProvider = object : ViewOutlineProvider() {

        override fun getOutline(view: View, outline: Outline) {
            val margin = Math.min(view.width, view.height) / 10

            outline.setOval(margin, margin, view.width - margin, view.height - margin)
        }
    }
}
