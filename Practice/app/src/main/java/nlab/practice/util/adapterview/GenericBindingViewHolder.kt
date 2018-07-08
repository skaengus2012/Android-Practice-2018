package nlab.practice.util.adapterview

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

/**
 * @author Doohyun
 * @since 2018. 07. 11
 */
open class GenericBindingViewHolder(
        private val viewDataBinding : ViewDataBinding,
        private var variableId : Int = NONE_VIEW_VAR_ID) : RecyclerView.ViewHolder(viewDataBinding.root) {

    companion object {
        private const val NONE_VIEW_VAR_ID = -1
    }

    fun onBindView(item : Any?) {
        item?.let {
            viewDataBinding.setVariable(variableId, item)
            viewDataBinding.executePendingBindings()
        }
    }
}