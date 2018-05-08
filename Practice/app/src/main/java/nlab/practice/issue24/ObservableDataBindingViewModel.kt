package nlab.practice.issue24

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import nlab.practice.common.model.User
import nlab.practice.common.repository.UserRepository
import nlab.practice.common.repository.UsersMutableLive

typealias UserObservable = ObservableField<User>

/**
 * 옵저버블 데이터 테스트를 위한 뷰모델 클래스 정의
 *
 * @author Doohyun
 */
class ObservableDataBindingViewModel : ViewModel() {

    val users : UsersMutableLive = UserRepository.getUsers().apply {
        observeForever {
            it?.takeIf { !it.isEmpty() }?.run {
                user.set(it[0])
            }
        }
    }

    val user : UserObservable = UserObservable()



}