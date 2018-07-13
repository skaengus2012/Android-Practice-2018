package nlab.practice.util.adapterview

import android.support.v7.widget.RecyclerView

/**
 * RecyclerView 의 제네릭 설정 정보 정의
 *
 * @author Doohyun
 * @since 2018. 07. 13
 */
data class GenericRecyclerViewConfig(
        var layoutManager : RecyclerView.LayoutManager
)