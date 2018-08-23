package nlab.practice.issue34

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.widget.RemoteViews

import nlab.practice.R

// 기준 사이즈 하드코딩...
// 사이즈를 정확히 맞출 수가 없음 (해상도 마다 다른 값이 들어오며, 해당 기준은 실험적으로 구글뮤직의 위젯으로 정의)
private const val LAYOUT_CHANGED_THREADS_HOLD = 125

/**
 * YouTube Music Copy Widget 정의
 *
 * 참고 사이트
 *
 * @see { https://developer.android.com/guide/practices/ui_guidelines/widget_design }
 *
 * @author Doohyun
 */
class YouTubeMusicCopyWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
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

        context?.let {
            context
            ->
            // 변경된 옵션 사이즈
            @LayoutRes val remoteViewLayout : Int =
                    newOptions?.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT)
                        ?.takeIf { it < LAYOUT_CHANGED_THREADS_HOLD }
                        ?.let { R.layout.you_tube_music_copy_widget }
                        ?: R.layout.you_tube_music_copy_widget_big


            appWidgetManager?.updateAppWidget(appWidgetId, RemoteViews(context.packageName, remoteViewLayout))
        }
    }

    companion object {

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int) {

            /**
            val widgetText = context.getString(R.string.appwidget_text)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.you_tube_music_copy_widget)
            views.setTextViewText(R.id.appwidget_text, widgetText)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)*/
        }
    }
}

