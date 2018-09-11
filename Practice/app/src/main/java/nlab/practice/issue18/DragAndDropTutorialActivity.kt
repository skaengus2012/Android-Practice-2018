package nlab.practice.issue18

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import nlab.practice.R
import android.widget.LinearLayout
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_drag_and_drop_tutorial.*


/**
 * Drag And Drop 튜토리얼 작업.
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/18
 *
 * @author ndh1002
 */
class DragAndDropTutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_and_drop_tutorial)

        createDragAndDropLongClickListener().let {
            textView.setOnLongClickListener(it)
            imageView.setOnLongClickListener(it)
            button.setOnLongClickListener(it)
        }

        createDragEventListener().let {
            layoutBlue.setOnDragListener(it)
            layoutRed.setOnDragListener(it)
            layoutYellow.setOnDragListener(it)
        }
    }

    /**
     * 드래그 앤 드롭을 할 수 있는 롱클릭 리스너를 출력
     *
     * @return
     */
    private fun createDragAndDropLongClickListener() : View.OnLongClickListener = View.OnLongClickListener {
        val dragShadowBuilder = View.DragShadowBuilder(it)

        it.visibility = View.INVISIBLE

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            it.startDragAndDrop(null, dragShadowBuilder, it, 0)
        } else {
            it.startDrag(null, dragShadowBuilder, it, 0)
        }
    }

    /**
     * 드래그 이벤트 리스너 생산.
     *
     * @return
     */
    private fun createDragEventListener() : View.OnDragListener = View.OnDragListener {
        v, event ->

        when(event.action) {
            DragEvent.ACTION_DRAG_STARTED,
            DragEvent.ACTION_DRAG_ENTERED,
            DragEvent.ACTION_DRAG_LOCATION,
            DragEvent.ACTION_DRAG_EXITED,
            DragEvent.ACTION_DRAG_ENDED -> true

            DragEvent.ACTION_DROP -> {

                val view = event.localState as View
                val owner = view.parent as ViewGroup
                owner.removeView(view)

                val container = v as LinearLayout
                container.addView(view)

                view.visibility = View.VISIBLE

                true
            }

            else -> false

        }
    }

}
