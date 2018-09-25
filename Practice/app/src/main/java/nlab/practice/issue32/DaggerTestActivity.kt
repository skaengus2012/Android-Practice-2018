package nlab.practice.issue32

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_dagger_test.*
import nlab.practice.R
import javax.inject.Inject

/**
 * Dagger Test 정의
 *
 * @author Doohyun
 */
class DaggerTestActivity : AppCompatActivity(), DaggerTestActivityContract.View {

    @Inject
    lateinit var presenter: DaggerTestActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger_test)

        AndroidInjection.inject(this)
    }

    override fun onResume() {
        super.onResume()

        presenter.requestMainDescription()
    }

    override fun setMainDescription(description: String) {
        tvMainDescription.text = description
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.clear()
    }
}
