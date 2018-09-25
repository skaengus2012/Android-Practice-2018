package nlab.practice.issue32

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import nlab.practice.R
import nlab.practice.util.resource.convertString
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author Doohyun
 */
class DaggerTestFragmentPresenterImpl @Inject constructor(private val _view: DaggerTestFragmentContract.View)
    : DaggerTestFragmentContract.Presenter {

    private val _disposable : CompositeDisposable by lazy { CompositeDisposable() }

    override fun requestMessage() {
        Observable.timer(5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext{
                    val message = convertString(R.string.label_dagger_fragment_test)
                    _view.bindMessage(message)
                }
                .subscribe()
                .addTo(_disposable)
    }

    override fun clear() {
        _disposable.clear()
    }
}