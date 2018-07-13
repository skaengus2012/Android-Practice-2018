package nlab.practice.issue24

import android.databinding.Bindable
import nlab.practice.R
import nlab.practice.common.model.User
import nlab.practice.util.adapterview.ItemViewModel

/**
 * 유저 뷰모델 정의
 *
 * @author Doohyun
 * @since 2018. 07. 13
 */
class SimpleUserItemViewModel(private val mUser : User) : ItemViewModel() {

    override fun getLayoutRes(): Int = R.layout.item_simple_user

    @Bindable fun getUserName() : String? = mUser.name
}