package nlab.practice.issue12

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_constraint_example_template.view.*
import nlab.practice.R

/**
 * ConstraintLayout 연습 레이아웃을 담을 리스트뷰의 어댑터 정의.
 *
 * @author ndh1002
 */
class LayoutSampleAdapter(private val items : List<LayoutSampleAdapter.Sample>)
    : RecyclerView.Adapter<LayoutSampleAdapter.LayoutSampleViewHolder>(){

    /**
     * 해당 어댑터에서 사용할 샘플.
     */
    data class Sample(val title : String, val itemView : View)

    /**
     * 해당 어댑터에 들어갈 뷰홀더 정의
     */
    class LayoutSampleViewHolder(convertView : View) : RecyclerView.ViewHolder(convertView) {
        private val tvTitle = convertView.tvTitle
        private val layoutExample = convertView.layoutExample

        /**
         * [sample] 을 받아 뷰에 바인드 한다.
         *
         * @param sample
         */
        fun bindData(sample : LayoutSampleAdapter.Sample) {
            tvTitle.text = sample.title

            layoutExample.run {
                removeAllViews()
                addView(sample.itemView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LayoutSampleViewHolder =
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_constraint_example_template, parent, false)
                    .let { LayoutSampleViewHolder(it) }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: LayoutSampleViewHolder, position: Int) {
        holder.bindData(items[position])
    }
}