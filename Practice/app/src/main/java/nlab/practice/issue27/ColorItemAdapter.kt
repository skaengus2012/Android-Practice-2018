package nlab.practice.issue27

import android.support.annotation.ColorRes
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_adapter_view_detail.view.*
import nlab.practice.R

/**
 * @author Doohyun
 * @since 2018. 07. 18
 */
sealed class ColorItemAdapter(items: List<Int>) : RecyclerView.Adapter<ColorItemAdapter.ViewHolder>() {

    private val _items = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            LayoutInflater.from(parent.context).inflate(getLayoutResourceId(), parent, false)
                    .let { ViewHolder(it, javaClass) }


    override fun getItemCount(): Int = _items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        @ColorRes val colorRes: Int = _items[position]
        holder.setColor(colorRes)
    }

    @LayoutRes abstract fun getLayoutResourceId() : Int

    class ViewHolder(view: View, clazz: Class<out ColorItemAdapter>) : RecyclerView.ViewHolder(view) {
        private val _ivColor = view.ivColor

        init {
            Log.w("ColorItemAdapter", "뷰홀더 생성 -> [${clazz.simpleName}]으로 부터 생성")
        }

        fun setColor(@ColorRes color: Int) = _ivColor.setImageResource(color)
    }

    class Type1(items : List<Int>) : ColorItemAdapter(items) {
        override fun getLayoutResourceId(): Int = R.layout.item_adapter_view_detail
    }

    class Type2(items: List<Int>) : ColorItemAdapter(items) {
        override fun getLayoutResourceId(): Int = R.layout.item_adapter_view_detail_2
    }

    class Type3(items: List<Int>) : ColorItemAdapter(items) {
        override fun getLayoutResourceId(): Int = R.layout.item_adapter_view_detail_3
    }
}