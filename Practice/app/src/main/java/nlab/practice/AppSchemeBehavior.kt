package nlab.practice

import android.app.Activity
import android.content.Intent
import android.net.Uri
import nlab.practice.common.CodeDefinition
import nlab.practice.issue34.PlayListManageActivity

/**
 * App Scheme 에 대한 행위 정의
 *
 * @author Doohyun
 */
fun doAppSchemeBehavior(activity: Activity, uri : Uri) {

    uri.getQueryParameter(CodeDefinition.SCHEME_INFO.QUERY_TARGET)?.let {
        target
        ->
        when(target) {
            CodeDefinition.ACTION_INTO.PARAM_GO_PLAYLIST -> goToPlayList(activity)
        }

    }
}

/**
 * Activity 이동 처리
 *
 * @param
 */
fun goToPlayList(activity : Activity) {
    val intent = Intent(activity, PlayListManageActivity::class.java)
    activity.startActivity(intent)
}