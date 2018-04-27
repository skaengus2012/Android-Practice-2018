package nlab.practice.issue11

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import nlab.practice.R
import nlab.practice.util.resource.convertColor

/**
 * 커스텀뷰를 이용한 프로그레스 바
 *
 * http://onecellboy.tistory.com/344
 *
 * @author ndh1002
 */
class MyProgressBar : View {

    private var isDraw = false

    private var currentValue: Int? = null
        set(value) {
            field = value
            requestLayoutAndInvalidateIfDrawCompleted()
        }

    private var maxValue: Int? = null
        set(value) {
            field = value
            requestLayoutAndInvalidateIfDrawCompleted()
        }

    private var lineColor: Int? = null
        set(value) {
            field = value
            requestLayoutAndInvalidateIfDrawCompleted()
        }

    private lateinit var paintCircle : Paint
    private lateinit var paintText :  Paint
    private val rectF = RectF()


    /**
     * MyProgressBar 에 대한 디폴트 세팅.
     */
    class DefaultValue {
        companion object {
            const val CURRENT_VALUE = 0
            const val MAX_VALUE = 0
            val LINE_COLOR = convertColor(R.color.colorPrimary)
        }
    }


    constructor(context: Context?) : super(context) {
        initPaintObject()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initPaintObject()
        createTypedArray(context, attrs)?.let { bindAttr(it) }
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initPaintObject()
        createTypedArray(context, attrs, defStyleAttr)?.let { bindAttr(it) }
    }

    /**
     * Draw 에 사용할 객체 초기화 작업.
     */
    private fun initPaintObject() {
        paintCircle = Paint().apply {
            color = lineColor ?: DefaultValue.LINE_COLOR
            strokeWidth = 10f
            isAntiAlias = true
            style = Paint.Style.STROKE
        }

        paintText = Paint().apply {
            color = Color.BLACK
            textAlign = Paint.Align.CENTER
        }
    }


    /**
     * TypedArray 생산
     *
     * @param context
     * @param attrs
     */
    private fun createTypedArray(context: Context?, attrs: AttributeSet?) : TypedArray? =
            context?.obtainStyledAttributes(attrs, R.styleable.MyProgressBar)

    /**
     * TypedArray 생산
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    private fun createTypedArray(context: Context?, attrs: AttributeSet?, defStyle : Int) : TypedArray? =
            context?.obtainStyledAttributes(attrs, R.styleable.MyProgressBar, defStyle, 0)

    /**
     * [typedArray] 값을 이용하여 attr 값 바인드.
     *
     * @param typedArray
     */
    private fun bindAttr(typedArray: TypedArray) {
        currentValue = typedArray.getInteger(R.styleable.MyProgressBar_currentValue, DefaultValue.CURRENT_VALUE)
        maxValue = typedArray.getInteger(R.styleable.MyProgressBar_maxValue, DefaultValue.MAX_VALUE)
        lineColor = typedArray.getColor(R.styleable.MyProgressBar_lineColor, DefaultValue.LINE_COLOR)

        typedArray.recycle()
    }

    /**
     * 그래프 표시
     *
     * @param canvas
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paintText.textSize = measuredWidth.toFloat() / 6

        canvas?.let {

            val currentValue = this.currentValue ?: DefaultValue.CURRENT_VALUE
            val maxValue = this.maxValue ?: DefaultValue.MAX_VALUE

            rectF.apply {
                left = 15f
                top = 15f
                right = measuredWidth.toFloat() - 15f
                bottom = measuredHeight.toFloat() - 15f
            }

            it.drawArc(
                    rectF,
                    -90f,
                    (currentValue.toFloat() / maxValue.toFloat()) * 360,
                    false,
                    paintCircle
            )

            it.drawText(
                    "$currentValue/$maxValue"
                    , (measuredWidth.toFloat()) / 2
                    , (measuredHeight.toFloat()) / 2
                    , paintText
            )
        }

        isDraw = true
    }

    /**
     * 뷰가 이미 그려진 상황이라면,
     * 뷰를 다시 그리고 레이아웃 배치도 다시한다.
     */
    private fun requestLayoutAndInvalidateIfDrawCompleted() {
        if (isDraw) {
            invalidate()
            requestLayout()
        }
    }
}