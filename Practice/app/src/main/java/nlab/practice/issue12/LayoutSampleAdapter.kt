package nlab.practice.issue12

import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_constraint_example_template.view.*
import nlab.practice.R
import nlab.practice.util.resource.convertString

/**
 * ConstraintLayout 연습 레이아웃을 담을 리스트뷰의 어댑터 정의.
 */
class LayoutSampleAdapter : RecyclerView.Adapter<LayoutSampleAdapter.LayoutSampleViewHolder>(){

    private val itemGroup = ViewType.values()

    /**
     * 해당 어댑터에 들어갈 뷰홀더 정의
     */
    class LayoutSampleViewHolder(
            convertView : View
            , viewType: ViewType) : RecyclerView.ViewHolder(convertView) {

        init {
            Log.i(LayoutSampleAdapter::javaClass.name, "세부적인 View 생성 ${viewType.name}")

            val view = LayoutInflater.from(convertView.context).inflate(viewType.layoutRes, null)
            convertView.layoutExample.addView(view)
            convertView.tvTitle.text = convertString(viewType.titlesRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LayoutSampleViewHolder =
            itemGroup[viewType].createViewHolder(parent)

    override fun getItemCount(): Int = itemGroup.size

    /**
     * [position] 에 해당하는 뷰타입 정보를 출력한다.
     *
     * @param position
     * @return
     */
    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: LayoutSampleViewHolder, position: Int) {
        // 어떤 작업도 하지 않아도 됨.
        // 타입 당, 아이템은 무조건 하나씩.
    }

    /**
     * ViewType 함수 제작.
     */
    enum class ViewType(@StringRes titlesRes : Int, @LayoutRes layoutRes : Int) {

        RelativePosition(R.string.constraint_layout_title_relative_position, R.layout.item_constraint_test_relative_postion),
        CenterPosition(R.string.constraint_layout_title_center_position, R.layout.item_constraint_test_center_position),
        VisibilityBehavior(R.string.constraint_layout_visibility_behavior, R.layout.item_constraint_test_visibility_behavior),
        Dimensions(R.string.constraint_layout_dimensions, R.layout.item_constraint_test_dimensions),
        Ratio(R.string.constraint_layout_ratio, R.layout.item_constraint_test_ratio)

        ;

        val titlesRes : Int = titlesRes
        val layoutRes : Int = layoutRes


        /**
         * [parent] 를 받아 뷰홀더 생산.
         *
         * @param parent
         * @return
         */
        fun createViewHolder(parent : ViewGroup) : LayoutSampleViewHolder =
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_constraint_example_template, parent, false)
                    .let { LayoutSampleViewHolder(it, this) }

    }
}