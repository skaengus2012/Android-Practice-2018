package nlab.practice.issue34

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import nlab.practice.common.model.Track

/**
 * Playlist Sync ViewModel
 *
 * @author Doohyun
 * @since 2018. 08. 23
 */
class PlayListManageViewModel(application: Application) : AndroidViewModel(application) {

    val track : ObservableField<Track> = ObservableField()

    /**
     * 현재 트랙 초기화
     */
    fun initCurrentTrack() {
        PlayListMockManager.currentPosition?.let { setTrack(it) }
    }

    /**
     * 다음 Track 으로 업데이트 처리를 수행한다.
     */
    fun playNext() {
        val isEmptyPlayList = PlayListMockManager.playLists.isEmpty()
        if (!isEmptyPlayList) {
            val playListSize = PlayListMockManager.playLists.size

            // 선택된 포지션에서 1 증가 처리, 없다면 0
            val position = PlayListMockManager.currentPosition
                    ?.let { (it + 1) % playListSize }
                    ?:0

            setTrack(position)
        }
    }

    /**
     * [position] 에 해당하는 트랙으로 세팅한다.
     *
     * @param position
     */
    private fun setTrack(position: Int) {
        val isValidPosition = PlayListMockManager.playLists.size > position
        if (isValidPosition) {
            val indexTrack = PlayListMockManager.playLists[position]

            // 뷰모델 업데이트
            track.set(indexTrack)

            // 매니저 업데이트
            TrackManager.lastListenedTrack = indexTrack
            PlayListMockManager.currentPosition = position
        }
    }

}