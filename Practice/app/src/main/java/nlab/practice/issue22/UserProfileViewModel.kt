package nlab.practice.issue22

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import nlab.practice.common.model.User
import nlab.practice.common.repository.UserRepository

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

    var user : UserMutableLive? = null

    /**
     * [name] 정보 업데이트.
     *
     * @param name
     */
    fun updateName(name : String) {
        user?.value?.run {
            this.name = name

            UserRepository.update(this)
        }
    }

    /**
     * 유저정보 리플레쉬.
     */
    fun refreshUser() {
        userId?.run { UserRepository.refreshUser(this, { user?.value = it }) }
    }
}