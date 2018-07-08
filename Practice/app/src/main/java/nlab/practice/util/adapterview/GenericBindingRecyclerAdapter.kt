package nlab.practice.util.adapterview

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
class GenericBindingRecyclerAdapter<T : BindAbleItem> : RecyclerView.Adapter<GenericBindingViewHolder>() {

    private var mItems : MutableList<T> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericBindingViewHolder =
            GenericBindingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false), BR.viewModel)

    // todo HEADER, FOOTER, EMPTY VIEW 고려할 것
    override fun getItemCount(): Int = mItems.size

    // todo HEADER, FOOTER, EMPTY VIEW 고려할 것
    override fun onBindViewHolder(holder: GenericBindingViewHolder, position: Int) {
        holder.onBindView(mItems[position])
    }

    // todo HEADER, FOOTER, EMPTY VIEW 고려할 것
    override fun getItemViewType(position: Int): Int {
        return mItems[position].getLayoutRes()
    }
}