package nlab.practice.issue30.page

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import nlab.practice.common.api.mock.MockUserWebService
import nlab.practice.common.model.User
import java.util.*

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val _disposable : CompositeDisposable by lazy { CompositeDisposable() }

    val users : ObservableArrayList<UserInfoItem> = ObservableArrayList()

    override fun onCleared() {
        super.onCleared()

        _disposable.clear()
    }

    /**
     * 유저 정보 조회
     */
    fun initUser() = Single.fromCallable { MockUserWebService.getUsers() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { users
                ->
                users.map { UserInfoItem(it) }
            }
            .doOnSuccess {
                users.clear()
                users.addAll(it)
            }
            .subscribe()
            .addTo(_disposable)

}