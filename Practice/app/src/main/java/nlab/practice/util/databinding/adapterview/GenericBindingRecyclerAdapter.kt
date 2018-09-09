package nlab.practice.util.databinding.adapterview

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR

/**
 * Generic 으로 담을 수 있는 RecyclerAdapter 정의
 *
 * @author Doohyun
 * @since 2018. 07. 11
 */
class GenericBindingRecyclerAdapter <T : BindAbleItem> : RecyclerView.Adapter<GenericBindingViewHolder>() {

    val items : MutableList<T> by lazy { ArrayList<T>() }
    var useHeaderYn : Boolean = false
    var useFooterYn : Boolean = false

    var headerViewItem : T? = null
    var footerViewItem : T? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericBindingViewHolder =
            GenericBindingViewHolder(
                    DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false),
                    BR.viewModel)

    override fun getItemCount(): Int {
        var size = items.size

        if (useHeaderYn) { ++size }
        if (useFooterYn) { ++size }

        return size
    }

    override fun onBindViewHolder(holder: GenericBindingViewHolder, position: Int) {
        when (position) {
            // 아이템 사이즈에 포함될 경우
            in getItemSizeRange() -> holder.onBindView(items[position])

            // 포지션 사이즈가 0 일경우
            0 -> headerViewItem?.run { holder.onBindView(this) }

            // 그 외라면, footer 세팅
            else -> footerViewItem?.run { holder.onBindView(this) }
        }
    }

    override fun getItemViewType(position: Int): Int = when(position) {
        // 아이템 사이즈에 포함될 경우
        in getItemSizeRange() -> items[position].getLayoutRes()

        // 포지션 사이즈가 0 일경우
        0 -> headerViewItem?.getLayoutRes() ?: 0

        // 그 외라면, footer 세팅
        else -> footerViewItem?.getLayoutRes() ?: 0
    }

    /**
     * 아이템 사이즈의 범위를 출력한다.
     */
    private fun getItemSizeRange() : IntRange {
        var startItemSize = 0
        var endItemSize = items.size - 1

        // HEADER 를 사용할 경우 position 을 1씩 올려주자.
        if (useHeaderYn) {
            ++startItemSize
            ++endItemSize
        }

        return startItemSize..endItemSize
    }
}