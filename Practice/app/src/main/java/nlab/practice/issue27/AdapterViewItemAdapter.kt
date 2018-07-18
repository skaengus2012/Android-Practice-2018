package nlab.practice.issue27

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable
import kotlinx.android.synthetic.main.item_adapter_view.view.*
import nlab.practice.R
import java.util.*

/**
 * 카드 뷰형태의 RecyclerView 정의
 *
 * @author Doohyun
 */
class AdapterViewItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val _items = arrayOf(0, 1, 0, 2, 0, 3, 0, 1, 0, 2, 0, 3, 0, 1, 0, 2)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val isSeparateViewType = viewType == 0
        return if(isSeparateViewType) {
            layoutInflater.inflate(R.layout.item_adapter_view_seperator, parent, false).let { SeparateTypeItemViewHolder(it) }
        } else {
            layoutInflater.inflate(R.layout.item_adapter_view, parent, false).let { AdapterViewItemViewHolder(it, viewType) }
        }
    }

    override fun getItemCount(): Int = _items.size

    override fun getItemViewType(position: Int): Int = _items[position]

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       // (holder as? AdapterViewItemViewHolder)?.let { (holder.adapterView.adapter).notifyDataSetChanged() }
    }

    /**
     * RecyclerView 를 가지는 ViewHolder 정의
     */
    class AdapterViewItemViewHolder(view : View, type : Int) : RecyclerView.ViewHolder(view) {

        val adapterView : RecyclerView = view.adapterView

        init {
            view.adapterView.let {
                val randomValue = Random().nextInt(5) + 3
                val items = createColorItems(randomValue)

                it.layoutManager = createHorizontalScrolledGridLayoutManager(it.context, randomValue)
                it.adapter = when(type) {
                    1 -> ColorItemAdapter.Type1(items)
                    2 -> ColorItemAdapter.Type2(items)
                    else -> ColorItemAdapter.Type3(items)
                }
            }
        }
    }

    /**
     * 분리형 타입 뷰호덜 정의
     */
    class SeparateTypeItemViewHolder(view : View) : RecyclerView.ViewHolder(view)
}

/**
 * 옆으로 스크롤할 수 있는 GridLayoutManager 를 생산한다.
 *
 * @param context
 * @param rowCount
 * @return
 */
fun createHorizontalScrolledGridLayoutManager(context : Context, rowCount: Int) : GridLayoutManager =
        GridLayoutManager(context, rowCount, GridLayoutManager.HORIZONTAL, false)

/**
 * [rowCount] 의 배수만큼 RGB 색상 값을 만들다.
 *
 * @return
 */
fun createColorItems(rowCount: Int) : List<Int> =
        Observable.range(0, rowCount * rowCount)
                .map { when(it % 3) {
                    0 -> R.color.red
                    1 -> R.color.green
                    else -> R.color.blue
                }}
                .toList()
                .blockingGet()