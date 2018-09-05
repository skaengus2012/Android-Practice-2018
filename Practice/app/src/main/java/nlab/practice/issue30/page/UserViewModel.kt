package nlab.practice.issue30.page

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import nlab.practice.common.api.mock.MockUserWebService
import nlab.practice.util.view.NavigationController
import nlab.practice.util.databinding.LiveEvent

/**
 * 유저의 총 데이터 목록을 조회하는 뷰모델 정의
 *
 * @author Doohyun
 */
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val _disposable : CompositeDisposable by lazy { CompositeDisposable() }
    private var _navigationController : NavigationController? = null

    val users : ObservableArrayList<UserInfoItemViewModel> = ObservableArrayList()
    val goToUserEndEvent = LiveEvent<UserInfoItemViewModel>()

    override fun onCleared() {
        super.onCleared()

        _disposable.clear()
    }

    fun setNavigationController(navigationController: NavigationController) {
        _navigationController = navigationController
    }

    /**
     * 유저 정보 조회
     */
    fun initUser() = Single.fromCallable { MockUserWebService.getUsers() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { users
                ->
                users.map { UserInfoItemViewModel(it, goToUserEndEvent) }
            }
            .doOnSuccess {
                users.clear()
                users.addAll(it)
            }
            .subscribe()
            .addTo(_disposable)

}