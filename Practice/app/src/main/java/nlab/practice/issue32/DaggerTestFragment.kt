package nlab.practice.issue32


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_dagger_test.*

import nlab.practice.R
import javax.inject.Inject

class DaggerTestFragment : Fragment(), DaggerTestFragmentContract.View {

    @Inject
    lateinit var presenter : DaggerTestFragmentContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dagger_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AndroidSupportInjection.inject(this)
        presenter.requestMessage()
    }

    override fun bindMessage(message: String) {
        tvMessage.text = message
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.clear()
    }
}
