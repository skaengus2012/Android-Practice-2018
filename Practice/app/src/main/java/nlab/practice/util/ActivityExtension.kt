package nlab.practice.util

import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity

/**
 * [strRes] 에서 String 추출
 *
 * Application Context 로부터 데이터 호출하도록 처리.
 *
 * @param
 */
fun AppCompatActivity.convertString(@StringRes strRes : Int) : String = applicationContext.getString(strRes)