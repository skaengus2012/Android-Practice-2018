package nlab.practice.util.resource

import android.support.annotation.StringRes
import android.widget.Toast
import nlab.practice.PracticeApplication

/**
 * [strRes] 에서 String 추출
 *
 * Application Context 로부터 데이터 호출하도록 처리.
 *
 * @param strRes
 * @return
 */
fun convertString(@StringRes strRes : Int) : String = PracticeApplication.getContext().getString(strRes)

/**
 * 토스트를 표시.
 *
 * @param message
 */
fun showToast(message : String) = Toast.makeText(PracticeApplication.getContext(), message, Toast.LENGTH_SHORT).show()