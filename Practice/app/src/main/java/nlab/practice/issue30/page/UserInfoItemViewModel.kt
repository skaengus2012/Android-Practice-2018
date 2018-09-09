package nlab.practice.issue30.page

import android.view.View
import nlab.practice.R
import nlab.practice.common.model.User
import nlab.practice.util.V4Pair
import nlab.practice.util.databinding.adapterview.BindAbleItem
import nlab.practice.util.databinding.LiveEvent
import nlab.practice.util.view.SharedElementFragmentSupportable

/**
 * UserInfo Item 의 뷰모델 정의
 *
 * @author Doohyun
 * @since 2018. 09. 03
 */
class UserInfoItemViewModel(val user: User, private val onclickEvent : LiveEvent<UserInfoItemViewModel>)
    : BindAbleItem, SharedElementFragmentSupportable {

    private var sharedElementView : View? = null

    override fun getLayoutRes(): Int = R.layout.item_user_info

    override fun getSharedElementPairs(): List<V4Pair<out View, String>> = sharedElementView
            ?.let { V4Pair.create(it, UserEndFragment.createProfileTransitionName(user.userId)) }
            ?.let { listOf(it) }
            ?: emptyList()

    fun onClickBehavior() {
        onclickEvent.value = this
    }
}