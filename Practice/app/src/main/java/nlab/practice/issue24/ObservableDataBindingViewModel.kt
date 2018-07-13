package nlab.practice.issue24

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import nlab.practice.common.model.User
import nlab.practice.common.repository.UserRepository
import nlab.practice.util.resource.add

/**
 * 옵저버블 데이터 테스트를 위한 뷰모델 클래스 정의
 *
 * @author Doohyun
 */
class ObservableDataBindingViewModel(application: Application) : AndroidViewModel(application) {
    private val mDisposable : CompositeDisposable = CompositeDisposable()

    val users : ObservableArrayList<SimpleUserItemViewModel> = ObservableArrayList()

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }

    /**
     * 데이터 조회
     */
    fun loadUser() {
        UserRepository.getUsersSingle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                            it.map { SimpleUserItemViewModel(it) }
                                .let {
                                    users.clear()
                                    users.addAll(it)
                                    Log.e("sadds", " " + users.size)
                                }
                        },

                        {it.printStackTrace()}
                )
                .add(mDisposable)
    }
}