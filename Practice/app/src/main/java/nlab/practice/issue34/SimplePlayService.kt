package nlab.practice.issue34

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import nlab.practice.R
import nlab.practice.common.CodeDefinition
import nlab.practice.util.resource.convertString
import nlab.practice.util.resource.showToast

/**
 * Simple Play Service
 *
 * @author Doohyun
 */
class SimplePlayService : Service() {

    override fun onBind(p0: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * 서비스 종료 시, 프로그램 종료
     */
    override fun onDestroy() {
        super.onDestroy()

        Log.i("Practice", "SimplePlayService finished")

        TrackManager.lastListenedTrack = null
        TrackManager.isPlayed = false

        releaseWidget(applicationContext)
    }

    /**
     * 인텐트에서 첨부받은 액션 행위를 수행한다
     *
     * @param intent
     * @param flags
     * @param startId
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.action?.let { action
            ->
            val currentTrackName =
                    TrackManager?.lastListenedTrack?.title
                            ?: convertString(R.string.label_error_song_title)

            when (action) {

                CodeDefinition.ACTION_INTO.ACTION_PLAY -> {
                    showToast("${String.format(convertString(R.string.msg_play), currentTrackName)}")

                    TrackManager.isPlayed = true
                    releaseWidget(applicationContext)
                }

                CodeDefinition.ACTION_INTO.ACTION_PAUSE -> {
                    showToast(R.string.msg_play_pause)

                    TrackManager.isPlayed = false
                    releaseWidget(applicationContext)
                }

                CodeDefinition.ACTION_INTO.ACTION_LIKE -> {
                    showToast("${String.format(convertString(R.string.msg_like), currentTrackName)}")
                }

                CodeDefinition.ACTION_INTO.ACTION_UNLIKE -> {
                    showToast("${String.format(convertString(R.string.msg_unlike), currentTrackName)}")
                }

                CodeDefinition.ACTION_INTO.ACTION_PLAY_PREVIOUS -> {
                    TrackManager.playPrev()
                    releaseWidget(applicationContext)
                }

                CodeDefinition.ACTION_INTO.ACTION_PLAY_NEXT -> {
                    TrackManager.playNext()
                    releaseWidget(applicationContext)
                }

            }
        }


        return Service.START_NOT_STICKY
    }
}