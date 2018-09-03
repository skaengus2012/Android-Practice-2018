package nlab.practice.issue30.page

import nlab.practice.R
import nlab.practice.common.model.User
import nlab.practice.util.adapterview.BindAbleItem

/**
 * @author Doohyun
 * @since 2018. 09. 03
 */
class UserInfoItem(val user: User): BindAbleItem {

    override fun getLayoutRes(): Int = R.layout.item_user_info
}