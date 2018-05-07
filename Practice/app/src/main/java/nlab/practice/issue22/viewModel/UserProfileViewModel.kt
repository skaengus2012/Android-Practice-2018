package nlab.practice.issue22.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import nlab.practice.issue22.model.User
import nlab.practice.issue22.repository.UserRepository

typealias UserLive = LiveData<User>
typealias UserMutableLive = MutableLiveData<User>

/**
 * 이름과 나이를 입력받는 뷰모델 정보 정의.
 *
 * @author Doohyun
 */
class UserProfileViewModel : ViewModel() {
    var userId : String? = null
    set(value) {
        field = value

        field?.run { user = UserRepository.getUser(this) }
    }

    var user : UserLive? = null


}