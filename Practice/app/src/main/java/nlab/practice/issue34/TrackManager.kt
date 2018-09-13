package nlab.practice.issue34

import android.graphics.Bitmap
import android.util.LruCache
import nlab.practice.PracticeApplication
import nlab.practice.common.model.Track
import nlab.practice.common.repository.PlayListMockRepository

/**
 * Track 을 관리하는 매니저 모듈 정의
 *
 * App 과 AppWidget 은 이 곳에서 데이터를 공유하도록 처리
 *
 * @author Doohyun
 * @since 2018. 08. 23
 */
object TrackManager {

    var isPlayed = false
    private val _BitmapCache : LruCache<String, Bitmap> = LruCache(1)

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

    /**
     * 다음 포지션으로 이동
     */
    fun playNext() {
        val isEmptyPlayList = PlayListMockRepository.playLists.isEmpty()
        if (!isEmptyPlayList) {
            val playListSize = PlayListMockRepository.playLists.size

            // 선택된 포지션에서 1 증가 처리, 없다면 0
            val selectedPosition = PlayListMockRepository.currentPosition
                        ?.let { (it + 1) % playListSize }
                        ?:0

            PlayListMockRepository.currentPosition = selectedPosition
            lastListenedTrack = PlayListMockRepository.playLists[selectedPosition]
        }
    }

    /**
     * 이전 포지션으로 이동
     */
    fun playPrev() {
        val isEmptyPlayList = PlayListMockRepository.playLists.isEmpty()
        if (!isEmptyPlayList) {
            val playListSize = PlayListMockRepository.playLists.size

            // 선택된 포지션에서 1 증가 처리, 없다면 0
            val selectedPosition= PlayListMockRepository.currentPosition
                            ?.let {
                                val minusOne = it - 1

                                if (minusOne < 0) {
                                    (minusOne + playListSize) % playListSize
                                } else {
                                    minusOne % playListSize
                                }
                            }
                            ?:0

            PlayListMockRepository.currentPosition = selectedPosition
            lastListenedTrack = PlayListMockRepository.playLists[selectedPosition]
        }
    }

    /**
     * [url] 에 해당하는 캐시가 있다면 조회
     */
    fun getBitmapCache(url: String) : Bitmap? = _BitmapCache[url]

    fun setBitmapCache(url: String, bitmap: Bitmap) {
        _BitmapCache.put(url, bitmap)
    }
}