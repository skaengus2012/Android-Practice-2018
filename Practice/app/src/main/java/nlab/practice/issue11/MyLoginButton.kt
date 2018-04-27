package nlab.practice.issue11

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.custom_my_login_button.view.*
import nlab.practice.R

/**
 * 커스텀 로긴 버튼 정의
 *
 * http://gun0912.tistory.com/38
 *
 * @author ndh1002
 */
class MyLoginButton : ConstraintLayout {

    var bg : Int? = null
    var textColor : Int? = null
    var text : String? = null


    /**
     * 리소스 디폴트 값 정의.
     */
    class DefaultValue {
        companion object {
            const val BG = Color.WHITE
            const val TEXT_COLOR = Color.BLACK
            const val text = ""
        }
    }

    constructor(context: Context?) : super(context) {
        initView()
        attachSettingValueInView()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
        createTypedArray(context, attrs)?.let { bindAttr(it) }
        attachSettingValueInView()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
        createTypedArray(context, attrs, defStyleAttr)?.let { bindAttr(it) }
        attachSettingValueInView()
    }

    /**
     * View 초기화.
     */
    private fun initView() {
        LayoutInflater.from(context)
                .inflate(R.layout.custom_my_login_button, this, false)
                .let { addView(it) }
    }

    /**
     * TypedArray 생산
     *
     * @param context
     * @param attrs
     */
    private fun createTypedArray(context: Context?, attrs: AttributeSet?) : TypedArray? =
            context?.obtainStyledAttributes(attrs, R.styleable.MyLoginButton)

    /**
     * TypedArray 생산
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    private fun createTypedArray(context: Context?, attrs: AttributeSet?, defStyle : Int) : TypedArray? =
            context?.obtainStyledAttributes(attrs, R.styleable.MyLoginButton, defStyle, 0)


    /**
     * [typedArray] 을 이용하여 attr 바인드.
     *
     * @param typedArray
     */
    private fun bindAttr(typedArray: TypedArray) {
        bg = typedArray.getColor(R.styleable.MyLoginButton_bg, DefaultValue.BG)
        textColor = typedArray.getColor(R.styleable.MyLoginButton_textColor, DefaultValue.TEXT_COLOR)
        text = typedArray.getString(R.styleable.MyLoginButton_text)

        typedArray.recycle()
    }

    /**
     * setting 값을 View 에 바인드.
     */
    private fun attachSettingValueInView() {
        layoutBg.setBackgroundColor(bg ?: DefaultValue.BG)

        tvLabel.text = text ?: DefaultValue.text
        tvLabel.setTextColor(textColor ?: DefaultValue.TEXT_COLOR)
    }

}