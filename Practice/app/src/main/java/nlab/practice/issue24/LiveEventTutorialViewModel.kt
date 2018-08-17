package nlab.practice.issue24

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import nlab.practice.util.databinding.LiveEvent

/**
 * Live Event 테트스트를 위한 뷰모델 정의
 *
 * @author Doohyun
 */
class LiveEventTutorialViewModel(application: Application) : AndroidViewModel(application) {
    val clickButtonEvent: LiveEvent<Void> = LiveEvent()
}