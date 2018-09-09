package nlab.practice.issue30.page

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import nlab.practice.R
import nlab.practice.common.api.mock.MockUserWebService
import nlab.practice.util.databinding.LiveEvent
import nlab.practice.util.databinding.adapterview.BindAbleItem
import nlab.practice.util.resource.convertString

/**
 * 유저의 총 데이터 목록을 조회하는 뷰모델 정의
 *
 * @author Doohyun
 */
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val _disposable : CompositeDisposable by lazy { CompositeDisposable() }

    private val headerViewModel = NavigationPageHeaderItemViewModel(convertString(R.string.menu_top_user_page))
    private val friendLineLabelModel = NavigationPageLabelItemViewModel(convertString(R.string.label_top_user_friends))

    val items : ObservableArrayList<BindAbleItem> = ObservableArrayList()
    val goToUserEndEvent = LiveEvent<UserInfoItemViewModel>()

    override fun onCleared() {
        super.onCleared()

        _disposable.clear()
    }

    /**
     * 유저 정보 조회
     */
    fun initUser() {
        val observables: MutableList<Observable<out BindAbleItem>> = mutableListOf(
                Single.just(headerViewModel).toObservable(),

                // 유저 정보
                Single.fromCallable { MockUserWebService.getUser("ID") }
                        .map {UserInfoItemViewModel(it, goToUserEndEvent)}
                        .toObservable(),

                Single.just(friendLineLabelModel).toObservable(),

                // 구성원 정보
                Single.fromCallable { MockUserWebService.getUsers() }
                        .flatMapObservable {
                            users
                            ->
                            // 아이템 10개 반복 추가
                            Observable.fromIterable(users)
                                    .repeat(10)
                                    .map { UserInfoItemViewModel(it, goToUserEndEvent) }
                        }
        )

        Observable.concat(observables)
                .compose { it.toList().toObservable() }
                .single(emptyList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess{
                    items.clear()
                    items.addAll(it)
                }
                .subscribe()
                .addTo(_disposable)
    }
}