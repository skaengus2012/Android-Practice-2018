package nlab.practice.issue27

import android.support.annotation.ColorRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_adapter_view_detail.view.*
import nlab.practice.R

/**
 * 컬러 아이템 세팅 어댑터
 *
 * @author Doohyun
 */
class ColorItemAdapter(items : List<Int>) : RecyclerView.Adapter<ColorItemAdapter.ViewHolder>() {

    init {
        println("뷰 생성")
    }

    private val _items = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_view_detail, parent, false)
                .let { ViewHolder(it) }


    override fun getItemCount(): Int = _items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        @ColorRes val colorRes : Int = _items[position]
        holder.setColor(colorRes)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val _ivColor = view.ivColor

        fun setColor(@ColorRes color : Int) = _ivColor.setImageResource(color)
    }

}