package nlab.practice.issue34

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

    var lastListenedTrack : Track? = null
}