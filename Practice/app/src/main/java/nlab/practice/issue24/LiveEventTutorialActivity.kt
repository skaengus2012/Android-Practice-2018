package nlab.practice.issue24

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_live_event_tutorial.*
import nlab.practice.R
import nlab.practice.databinding.ActivityLiveEventTutorialBinding
import nlab.practice.util.resource.showToast

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

        binding.viewModel =
                ViewModelProviders.of(this)
                    .get(LiveEventTutorialViewModel::class.java)
                    .apply { observeEvent(this) }
    }

    /**
     * bind event.
     *
     * -> 이 메소드를 뷰모델에 구현할 수도 있지만, Activity 와의 결합관계를 제거하기 위해 이 곳에 있는 것이 맞음
     *
     * @param viewModel
     */
    private fun observeEvent(viewModel: LiveEventTutorialViewModel) {
        viewModel.clickButtonEvent.observe(this, Observer { _ -> showToast("Name : ${getNameText()}")})
    }

    /**
     * get Name
     *
     * @return
     */
    private fun getNameText() : String = editText.text.toString()
}
