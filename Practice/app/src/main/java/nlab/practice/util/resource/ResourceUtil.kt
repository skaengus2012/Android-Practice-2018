package nlab.practice.util.resource

import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
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
 * [colorRes] 에서 Color 값을 추출
 *
 * Application Context 로부터 데이터 호출하도록 처리.
 *
 * @param colorRes
 * @return
 */
fun convertColor(@ColorRes colorRes: Int) : Int = ContextCompat.getColor(PracticeApplication.getContext(), (colorRes))

/**
 * [drawableRes] 에서 Color 값을 추출
 *
 * @param drawableRes
 * @return
 */
fun convertDrawable(@DrawableRes drawableRes: Int) = ContextCompat.getDrawable(PracticeApplication.getContext(), drawableRes)

/**
 * [dimenRes] 를 받아 Pixel 값을 출력
 *
 * @param context
 * @param dimenRes
 * @return
 */
fun getDimenPixelSize(context: Context, @DimenRes dimenRes: Int) : Int = context.resources.getDimensionPixelSize(dimenRes)

/**
 * 토스트를 표시.
 *
 * @param message
 */
fun showToast(message : String) = Toast.makeText(PracticeApplication.getContext(), message, Toast.LENGTH_SHORT).show()

/**
 * 토스트를 표시.
 *
 * @param strRes
 */
fun showToast(@StringRes strRes : Int) = showToast(convertString(strRes))