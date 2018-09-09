package nlab.practice.util.view

import android.view.View
import nlab.practice.util.V4Pair

/**
 * Shared Element Support 를 위한 인터페이스 부 정의
 *
 * @author Doohyun
 * @since 2018. 09. 05
 */
interface SharedElementFragmentSupportable {

    /**
     * Shared Element 에 사용할 Element 종류 정의
     *
     * @return Shared Element 에 사용할 Pair 목록
     */
    fun getSharedElementPairs() : List<V4Pair<out View, String>>
}