package nlab.practice.issue34

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.View
import android.widget.RemoteViews
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener

import nlab.practice.R
import nlab.practice.common.CodeDefinition
import nlab.practice.common.REQUEST_CODE_GO_TO_PLAYLIST
import nlab.practice.util.GlideApp
import nlab.practice.util.resource.convertString
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


// 기준 사이즈 하드코딩...
// 사이즈를 정확히 맞출 수가 없음 (해상도 마다 다른 값이 들어오며, 해당 기준은 실험적으로 구글뮤직의 위젯으로 정의)
private const val LAYOUT_CHANGED_THREADS_HOLD = 125

/**
 * YouTubeMusicCopyWidgetProvider 클래스 기준으로 리모트뷰를 초기화한다.
 *
 * @param context
 */
internal fun releaseWidget(context: Context?) {
    val componentName = ComponentName(context, YouTubeMusicCopyWidgetProvider::class.java.name)

    val appWidgetManager = AppWidgetManager.getInstance(context)

    appWidgetManager.getAppWidgetIds(componentName).forEach { releaseWidget(context, appWidgetManager, it) }
}

/**
 * [appWidgetManager] 에 적용된 옵션으로 레이아웃을 지정한다.
 *
 * @param context
 * @param appWidgetManager
 * @param appWidgetId
 */
internal fun releaseWidget(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetId: Int) =
        appWidgetManager
                ?.getAppWidgetOptions(appWidgetId)
                ?.let { releaseWidget(context, appWidgetManager, appWidgetId, it) }

/**
 * [options] 의 높이 값에 따라 분기된 레이아웃으로 remoteView 적용한다.
 *
 * @param context
 * @param appWidgetManager
 * @param appWidgetId
 * @param options
 */
private fun releaseWidget(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int,
        options: Bundle?) = context?.let { context
    ->
    // 옵션의 최소 높이로 지정된 임계점을 기준으로 레이아웃을 나눈다.
    @LayoutRes val remoteViewLayout: Int =
            options?.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT)
                    ?.takeIf { it < LAYOUT_CHANGED_THREADS_HOLD }
                    ?.let { R.layout.you_tube_music_copy_widget }
                    ?: R.layout.you_tube_music_copy_widget_big

    // 리모트뷰 생산
    val remoteView = createRemoteView(context, remoteViewLayout, appWidgetId)

    // 위젯 데이터 업데이트
    appWidgetManager?.updateAppWidget(appWidgetId, remoteView)
}

/**
 * RemoteView 생산
 *
 * @param context
 * @param layoutRes
 * @param appWidgetId
 * @return
 */
private fun createRemoteView(context: Context, @LayoutRes layoutRes: Int, appWidgetId: Int): RemoteViews {
    val view = RemoteViews(context.packageName, layoutRes)

    // 공통 설정영역 세팅
    view.setOnClickPendingIntent(
            R.id.ivAlbum,
            createLaunchingPendingIntent(context, CodeDefinition.ACTION_INTO.PARAM_GO_PLAYLIST)
    )

    // 현재 듣는 음악이 존재할 경우 RemoteView 에 데이터 세팅
    TrackManager.lastListenedTrack
            ?.let { track
                ->
                // 타이틀 세팅
                String.format(
                        "%s - %s",
                        track.title ?: convertString(R.string.label_error_song_title),
                        track.artist ?: convertString(R.string.label_error_artist)
                ).run { view.setTextViewText(R.id.tvSongTitle, this) }


                val requestOptions = RequestOptions()
                        .placeholder(R.drawable.ic_music_note_black_24dp)
                        .error(R.drawable.ic_music_note_black_24dp)

                // FIXME 20180828
                // 해당 모듈을 사용할 시, 이미지 로드 실패 시, placeHolder 를 세팅해주지 않고 있음...
                val thumbnailUrl = track.albumImg ?: ""
                TrackManager.getBitmapCache(thumbnailUrl)
                        ?.let { view.setImageViewBitmap(R.id.ivAlbum, it) }
                        ?: run { refreshThumbnail(context, thumbnailUrl) }

                // 버튼 색 활성화
                view.setImageViewResource(R.id.ivLike, R.drawable.ic_thumb_up_black_24dp)
                view.setImageViewResource(R.id.ivPrev, R.drawable.ic_chevron_left_black_24dp)
                view.setImageViewResource(R.id.ivPlay, R.drawable.ic_play_arrow_black_24dp)
                view.setImageViewResource(R.id.ivNext, R.drawable.ic_chevron_right_black_24dp)
                view.setImageViewResource(R.id.ivUnLike, R.drawable.ic_thumb_down_black_24dp)

                // 플레이 상태에 따른 플레이 버튼 처리
                if (TrackManager.isPlayed) {
                    view.setViewVisibility(R.id.btnPlay, View.GONE)
                    view.setViewVisibility(R.id.btnPause, View.VISIBLE)
                } else {
                    view.setViewVisibility(R.id.btnPlay, View.VISIBLE)
                    view.setViewVisibility(R.id.btnPause, View.GONE)
                }

                view.setOnClickPendingIntent(R.id.btnPlay, createCommandActionPendingIntent(context, CodeDefinition.ACTION_INTO.ACTION_PLAY))
                view.setOnClickPendingIntent(R.id.btnPause, createCommandActionPendingIntent(context, CodeDefinition.ACTION_INTO.ACTION_PAUSE))
                view.setOnClickPendingIntent(R.id.btnLike, createCommandActionPendingIntent(context, CodeDefinition.ACTION_INTO.ACTION_LIKE))
                view.setOnClickPendingIntent(R.id.btnUnLike, createCommandActionPendingIntent(context, CodeDefinition.ACTION_INTO.ACTION_UNLIKE))
                view.setOnClickPendingIntent(R.id.btnNext, createCommandActionPendingIntent(context, CodeDefinition.ACTION_INTO.ACTION_PLAY_NEXT))
                view.setOnClickPendingIntent(R.id.btnPrev, createCommandActionPendingIntent(context, CodeDefinition.ACTION_INTO.ACTION_PLAY_PREVIOUS))

            }
            ?: run {
                // 타이틀 세팅
                view.setTextViewText(R.id.tvSongTitle, convertString(R.string.label_music_name))

                // 이미지 세팅
                view.setImageViewResource(R.id.ivAlbum, R.drawable.ic_music_note_black_24dp)

                // 버튼 색 활성화
                view.setImageViewResource(R.id.ivLike, R.drawable.ic_thumb_up_gray_24dp)
                view.setImageViewResource(R.id.ivPrev, R.drawable.ic_chevron_left_gray_24dp)
                view.setImageViewResource(R.id.ivPlay, R.drawable.ic_play_arrow_gray_24dp)
                view.setImageViewResource(R.id.ivNext, R.drawable.ic_chevron_right_gray_24dp)
                view.setImageViewResource(R.id.ivUnLike, R.drawable.ic_thumb_down_gray_24dp)

                view.setViewVisibility(R.id.btnPlay, View.VISIBLE)
                view.setViewVisibility(R.id.btnPause, View.GONE)
            }

    return view
}

/**
 * [thumbnailImage] 에 대한 이미지 조회
 *
 * 이미지 조회에 성공 혹은 실패에 대하여 캐시 저장을 수행함
 */
private fun refreshThumbnail(context: Context, thumbnailImage: String) {
    GlideApp.with(context)
            .asBitmap()
            .load(thumbnailImage)
            .override(500, 500)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                    TrackManager.setBitmapCache(thumbnailImage, createDefaultBitmap(context))
                    releaseWidget(context)
                    return false
                }

                override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    TrackManager.setBitmapCache(thumbnailImage, resource?: createDefaultBitmap(context))
                    releaseWidget(context)
                    return false
                }
            })
            .submit()
}

/**
 * 기본 Bitmap 이미지 세팅
 *
 * // note : 해당 이미지는 오직 png 이어야함
 */
private fun createDefaultBitmap(context: Context) = BitmapFactory.decodeResource(context.resources, R.mipmap.common_img_track_default)

/**
 * [actionCode] 에 해당하는 화면으로 이동하는 처리 인텐트 실행
 *
 * @param context
 * @param actionCode
 * @return
 */
private fun createLaunchingPendingIntent(context: Context, actionCode: String): PendingIntent {
    val intent = Intent(context, YouTubeMusicCopyWidgetProvider::class.java)
            .apply { action = actionCode }

    return PendingIntent.getBroadcast(
            context,
            REQUEST_CODE_GO_TO_PLAYLIST,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT)
}

/**
 * [actionCode] 를 이용하여 서비스에 액션을 수행한다.
 *
 * @param context
 * @param actionCode
 * @return
 */
private fun createCommandActionPendingIntent(context: Context, actionCode: String): PendingIntent {
    val intent = Intent(context, SimplePlayService::class.java)
            .apply { action = actionCode }

    return PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
}

/**
 * YouTube Music Copy Widget 정의
 *
 * 참고 사이트
 *
 * @see { https://developer.android.com/guide/practices/ui_guidelines/widget_design }
 *
 * @author Doohyun
 */
class YouTubeMusicCopyWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) =
            appWidgetIds.forEach { releaseWidget(context, appWidgetManager, it) }


    override fun onEnabled(context: Context) = releaseWidget(context)

    override fun onRestored(context: Context?, oldWidgetIds: IntArray?, newWidgetIds: IntArray?) = releaseWidget(context)

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        intent?.action?.let { action
            ->
            when (action) {
                CodeDefinition.ACTION_INTO.PARAM_GO_PLAYLIST -> {
                    val newIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("${CodeDefinition.SCHEME_INFO.SCHEME}://${CodeDefinition.SCHEME_INFO.HOST}?${CodeDefinition.SCHEME_INFO.QUERY_TARGET}=$action")
                    )

                    newIntent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    newIntent.putExtras(intent)

                    context?.startActivity(newIntent)
                }

                else -> {
                }
            }
        }
    }

    /**
     * 위젯의 옵션이 변경될 때 아래 메소드에서 레이아웃을 변경할 수 있음
     *
     * @param context
     * @param appWidgetManager
     * @param appWidgetId
     * @param newOptions
     */
    override fun onAppWidgetOptionsChanged(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetId: Int, newOptions: Bundle?) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)

        // 리모트 뷰 지정
        releaseWidget(context, appWidgetManager, appWidgetId, newOptions)
    }
}

