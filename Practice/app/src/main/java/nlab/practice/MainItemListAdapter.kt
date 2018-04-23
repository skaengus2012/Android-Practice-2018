package nlab.practice

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_main.view.*
import nlab.practice.util.resource.ActivityConfigManager
import kotlin.reflect.KClass

/**
 * 메인 아이템에 들어갈 항목 정의.
 *
 * @author ndh1002
 */
class MainItemListAdapter : RecyclerView.Adapter<MainItemListAdapter.MainItemViewHolder>(){

    private val items : List<MainItem>

    init {
        items = ActivityConfigManager.getActivityKClassis()
                .filter { ActivityConfigManager.isHaveLabel(it) }
                .map { MainItem(ActivityConfigManager.getLabel(it)!!, it) }
    }

    /**
     * MainItemListAdapter 에서 사용하는 ViewHolder 정의.
     */
    class MainItemViewHolder(itemView : View)
        : RecyclerView.ViewHolder(itemView) {

        val tvLabel : TextView? = itemView.tvLabel
    }

    data class MainItem(val label : String, val clazz: KClass<out Activity>)

    /**
     * View Holder 생산
     *
     * @param parent
     * @param viewType
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainItemViewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
                    .let { MainItemViewHolder(it) }


    /**
     * 현재의 item 개수를 출력.
     */
    override fun getItemCount(): Int = items.size

    /**
     * ViewHolder 와 아이템 바인드.
     */
    override fun onBindViewHolder(holder: MainItemViewHolder, position: Int) {
        val item = items[position]

        holder.tvLabel?.let {
            // 텍스트 바인드.
            it.text = item.label

            // 클릭 시, 액티비티 전환 처리.
            (it.parent as? View)?.setOnClickListener({
                v -> Intent(v.context, item.clazz.java).let { v.context.startActivity(it) }
            })
        }
    }
}