package nlab.practice.issue32

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_dagger_test.*
import nlab.practice.R
import nlab.practice.util.view.NavigationController
import javax.inject.Inject

/**
 * Dagger Test 정의
 *
 * @see { https://cmcmcmcm.blog/2017/08/15/dagger2-를-이용한-안드로이드에서의-di-구현/ }
 *
 * @author Doohyun
 */
class DaggerTestActivity : AppCompatActivity(), DaggerTestActivityContract.View, HasSupportFragmentInjector {

    @Inject
    lateinit var presenter: DaggerTestActivityContract.Presenter

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private val _navigationController : NavigationController by lazy {
        NavigationController(R.id.layoutFragment, supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger_test)

        AndroidInjection.inject(this)
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

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
}
