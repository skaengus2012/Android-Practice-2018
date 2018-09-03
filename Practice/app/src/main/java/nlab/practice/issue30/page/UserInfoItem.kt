package nlab.practice.issue30.page

import android.databinding.ViewDataBinding
import android.view.View
import kotlinx.android.synthetic.main.item_user_info.view.*
import nlab.practice.R
import nlab.practice.common.model.User
import nlab.practice.util.adapterview.BindAbleItem
import nlab.practice.util.databinding.LiveEvent

/**
 * @author Doohyun
 * @since 2018. 09. 03
 */
class UserInfoItem(val user: User, private val onclickEvent : LiveEvent<UserInfoItem>) : BindAbleItem {

    private var sharedElementView : View? = null

    override fun getLayoutRes(): Int = R.layout.item_user_info

    override fun setBindingBehavior(binding: ViewDataBinding) {
        sharedElementView = binding.root.ivProfile
    }

    fun getSharedElementView() : View? = sharedElementView

    fun onClickBehavior() {
        onclickEvent.value = this
    }
}