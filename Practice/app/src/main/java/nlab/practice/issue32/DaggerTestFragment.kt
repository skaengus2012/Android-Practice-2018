package nlab.practice.issue32

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_dagger_test.*

import nlab.practice.R
import javax.inject.Inject

class DaggerTestFragment : DaggerFragment(), DaggerTestFragmentContract.View {

    @Inject
    lateinit var presenter : DaggerTestFragmentContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dagger_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
