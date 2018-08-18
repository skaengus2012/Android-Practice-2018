package nlab.practice.issue30.page

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import nlab.practice.common.api.mock.MockUserWebService
import nlab.practice.common.model.User
import nlab.practice.util.resource.add
import java.util.*

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val _disposable : CompositeDisposable by lazy { CompositeDisposable() }

    val user : ObservableField<User> = ObservableField()

    override fun onCleared() {
        super.onCleared()

        _disposable.clear()
    }

    /**
     * 유저 정보 조회
     */
    fun initUser() {
        Single.fromCallable {
                    val users = MockUserWebService.getUsers()

                    Collections.shuffle(users)

                    users[0]
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { user -> this.user.set(user) }
                .add(_disposable)
    }
}