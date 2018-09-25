package nlab.practice.issue32

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Dagger Presenter 기능 정의
 *
 * @author Doohyun
 */
class DaggerTestPresenterImpl @Inject constructor(private val _view: DaggerTestActivityContract.View)
    : DaggerTestActivityContract.Presenter {

    private val _disposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun requestMainDescription() {
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext{ _view.setMainDescription("Hello Dagger Injection!") }
                .subscribe()
                .addTo(_disposable)
    }

    override fun clear() = _disposable.clear()
}