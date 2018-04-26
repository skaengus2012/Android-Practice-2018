package nlab.practice.issue11

import android.content.Context
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

    private var paintCircle : Paint? = null
    private var paintText :  Paint? = null

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


    constructor(context: Context?) : super(context)

    /**
     * [attrs] 을 이용한 뷰 초기화.
     *
     * @param context
     * @param attrs
     */
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {

        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.MyProgressBar)

        currentValue = typedArray?.getInteger(R.styleable.MyProgressBar_currentValue, DefaultValue.CURRENT_VALUE)
        maxValue = typedArray?.getInteger(R.styleable.MyProgressBar_maxValue, DefaultValue.MAX_VALUE)
        lineColor = typedArray?.getColor(R.styleable.MyProgressBar_lineColor, DefaultValue.LINE_COLOR)

        typedArray?.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        paintCircle = Paint().apply {
            color = lineColor ?: DefaultValue.LINE_COLOR
            strokeWidth = 10f
            isAntiAlias = true
            style = Paint.Style.STROKE
        }

        // 텍스트에 대한 그래프.
        paintText = Paint().apply {
            color = Color.BLACK
            textSize = measuredWidth.toFloat() / 6
            textAlign = Paint.Align.CENTER
        }
    }

    /**
     * 그래프 표시
     *
     * @param canvas
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let {

            val currentValue = this.currentValue ?: DefaultValue.CURRENT_VALUE
            val maxValue = this.maxValue ?: DefaultValue.MAX_VALUE

            it.drawArc(
                    RectF(15f, 15f, measuredWidth.toFloat() - 15f, measuredHeight.toFloat() - 15f),
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