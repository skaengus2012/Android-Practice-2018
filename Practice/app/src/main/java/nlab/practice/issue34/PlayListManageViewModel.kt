package nlab.practice.issue34

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import nlab.practice.common.model.Track
import nlab.practice.common.repository.PlayListMockRepository

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
        TrackManager.lastListenedTrack?.let { track.set(it) }
    }

    /**
     * 다음 Track 으로 업데이트 처리를 수행한다.
     */
    fun playNext() {
        TrackManager.playNext()
        initCurrentTrack()
    }
}