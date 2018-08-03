package nlab.practice.issue28

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_long_text_test.*
import nlab.practice.R
import nlab.practice.databinding.ActivityLongTextTestBinding

/**
 * Long Text Ellipsize 에 대한 테스트
 *
 * @author Doohyun
 */
class LongTextEllipsizeTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityLongTextTestBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_long_text_test)

        ViewModelProviders.of(this)
                .get(LongTextEllipsizeTestViewModel::class.java)
                .let {
                    binding.viewModel = it

                    it.naveViewInvalidateEvent.observe(
                            LongTextEllipsizeTestActivity@this,
                            Observer {
                                tvName.postInvalidate()
                                layoutContents.postInvalidate()
                            }
                    )
                }
    }
}
