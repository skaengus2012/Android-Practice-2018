package nlab.practice.issue32

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_dagger_test.*
import nlab.practice.R
import nlab.practice.util.view.NavigationController
import javax.inject.Inject

/**
 * Contribute 를 사용한 DaggerTestActivity
 *
 * @author Doohyun
 */
class DaggerTestContributeActivity : DaggerAppCompatActivity(),  DaggerTestActivityContract.View {

    @Inject
    lateinit var presenter: DaggerTestActivityContract.Presenter

    private val _navigationController : NavigationController by lazy {
        NavigationController(R.id.layoutFragment, supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger_test)
    }

    override fun onResume() {
        super.onResume()

        presenter.requestMainDescription()

        _navigationController.goToDaggerTestFragment()
    }

    override fun setMainDescription(description: String) {
        tvMainDescription.text = description
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.clear()
    }
}