package nlab.practice.util

import android.support.v4.util.Pair

/**
 * typealias 를 묶어놓은 파일 영역 정의
 *
 * @author Doohyun
 * @since 2018. 09. 05
 */


/**
 * Android support v4 에 대한 Pair.
 *
 * Kotlin 내장 Pair 때문에 import 시 툴 이상이 일어남.
 */
typealias V4Pair<K, V> = Pair<K, V>