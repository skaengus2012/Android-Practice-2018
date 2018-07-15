package nlab.practice.issue24

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Observer
import nlab.practice.util.databinding.LiveEvent

/**
 * Live Event 테트스트를 위한 뷰모델 정의
 *
 * @author Doohyun
 */
class LiveEventTutorialViewModel(application: Application) : AndroidViewModel(application) {

    val clickButtonEvent: LiveEvent<Void> = LiveEvent()

    fun observeEvent(activity: LiveEventTutorialActivity) {
        clickButtonEvent.observe(activity, Observer { _
            ->
            activity.showToast()
        })
    }
}