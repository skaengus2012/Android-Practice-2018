package nlab.practice.issue30.page

import android.view.View
import kotlinx.android.synthetic.main.fragment_user_end.view.*
import nlab.practice.R
import nlab.practice.common.model.User
import nlab.practice.util.databinding.adapterview.BindAbleItem
import nlab.practice.util.databinding.LiveEvent

/**
 * UserInfo Item 의 뷰모델 정의
 *
 * @author Doohyun
 * @since 2018. 09. 03
 */
class UserInfoItemViewModel(val user: User, private val onclickEvent: LiveEvent<Pair<View, User>>)
    : BindAbleItem {

    override fun getLayoutRes(): Int = R.layout.item_user_info

    fun onClickEvent(view: View) {
        onclickEvent.value = Pair(view.ivProfile, user)
    }
}