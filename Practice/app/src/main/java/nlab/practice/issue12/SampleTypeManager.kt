package nlab.practice.issue12

import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import nlab.practice.R

/**
 * ViewType 을 가지고 있는 싱글톤 클래스.
 *
 * @author ndh1002
 */
object SampleTypeManager {

    /**
     * Sample 에 타입 정보 정의
     */
    data class SampleType(@StringRes val titlesRes : Int, @LayoutRes val layoutRes : Int)

    private val sampleTypes = listOf(
            SampleType(R.string.constraint_layout_title_side, R.layout.item_constraint_side),
            SampleType(R.string.constraint_layout_title_center, R.layout.item_constraint_test_center),
            SampleType(R.string.constraint_layout_visibility_behavior, R.layout.item_constraint_test_visibility_behavior),
            SampleType(R.string.constraint_layout_dimensions, R.layout.item_constraint_test_dimensions),
            SampleType(R.string.constraint_layout_ratio, R.layout.item_constraint_test_ratio),
            SampleType(R.string.constraint_layout_guideLine, R.layout.item_constraint_test_guide_line),
            SampleType(R.string.constraint_layout_barrier, R.layout.item_constraint_test_barreir),
            SampleType(R.string.constraint_layout_chain_style_spread, R.layout.item_constraint_test_chain_style_spread),
            SampleType(R.string.constraint_layout_chain_style_spread_inside, R.layout.item_constraint_test_chain_style_spread_inside),
            SampleType(R.string.constraint_layout_chain_style_weighted, R.layout.item_constraint_test_chain_style_weighted),
            SampleType(R.string.constraint_layout_chain_style_packed, R.layout.item_constraint_test_chain_style_packed)
    )

    /**
     * sample 정보 출력. (copy) 하여 넘김처리.
     *
     * @return
     */
    fun getSamples() : List<SampleType> = sampleTypes.toList()
}