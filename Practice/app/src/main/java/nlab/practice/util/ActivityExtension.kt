package nlab.practice.util

import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity

/**
 * [strRes] 에서 String 추출
 *
 * @param
 */
fun AppCompatActivity.convertString(@StringRes strRes : Int) : String = applicationContext.getString(strRes)