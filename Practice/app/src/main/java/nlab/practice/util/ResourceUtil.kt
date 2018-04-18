package nlab.practice.util

import android.support.annotation.StringRes
import nlab.practice.PracticeApplication

// Resource Util 의 기능을 이곳에 제작해봄.

/**
 * [strRes] 에서 String 추출
 *
 * @param
 */
fun GetString(@StringRes strRes : Int) : String = PracticeApplication.getContext().getString(strRes)