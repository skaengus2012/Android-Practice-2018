package nlab.practice.issue34

import nlab.practice.PracticeApplication
import nlab.practice.common.model.Track

/**
 * Track 을 관리하는 매니저 모듈 정의
 *
 * App 과 AppWidget 은 이 곳에서 데이터를 공유하도록 처리
 *
 * @author Doohyun
 * @since 2018. 08. 23
 */
object TrackManager {

    var lastListenedTrack: Track? = null
        set(value) {
            // 현재 입력된 Track 의 ID 와 동일한가?
            val isEqualsId: Boolean =
                    field?.id
                            ?.let { id -> value?.id?.let { id == it } ?: false }
                            ?: false

            // 데이터 업데이트
            field = value

            // 입력된 데이터가 다를 경우 위젯 초기화
            if (!isEqualsId) {
                releaseWidget(PracticeApplication.getContext())
            }
        }
}