package nlab.practice.issue30.page


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_note.view.*

import nlab.practice.R
import nlab.practice.common.api.mock.MockUserWebService
import nlab.practice.common.model.User
import nlab.practice.util.databinding.bindProfile
import nlab.practice.util.view.NavigationController

/**
 * Note 화면 정의
 *
 * @author Doohyun
 */
class NoteFragment : Fragment() {

    private val _disposable : CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private val _navigationController : NavigationController by lazy {
        NavigationController(R.id.layoutFragment, childFragmentManager)
    }

    private var user : User? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_note, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Single.fromCallable {  MockUserWebService.getUser("ID") }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    user = it
                    bindProfile(view.ivProfile, it.profile)
                }
                .subscribe()
                .addTo(_disposable)

        view.btnNext.setOnClickListener { user?.run {  _navigationController.goToUserEnd(this) } }
    }
}
