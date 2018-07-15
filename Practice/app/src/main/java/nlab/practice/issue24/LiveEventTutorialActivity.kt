package nlab.practice.issue24

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_live_event_tutorial.*
import nlab.practice.R
import nlab.practice.databinding.ActivityLiveEventTutorialBinding

/**
 * Live Event 테트스트를 위한 화면 정의
 *
 * @author Doohyun
 */
class LiveEventTutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityLiveEventTutorialBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_live_event_tutorial)

        val viewModel = ViewModelProviders.of(this)
                .get(LiveEventTutorialViewModel::class.java)
                .apply { observeEvent(this@LiveEventTutorialActivity) }

        binding.viewModel = viewModel
    }

    /**
     * Show simple Toast
     */
    fun showToast() = nlab.practice.util.resource.showToast("Name : ${getNameText()}")

    /**
     * get Name
     *
     * @return
     */
    fun getNameText() : String = editText.text.toString()
}
