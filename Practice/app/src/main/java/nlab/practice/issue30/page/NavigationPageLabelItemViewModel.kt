package nlab.practice.issue30.page

import nlab.practice.R
import nlab.practice.util.databinding.adapterview.BindAbleItem

/**
 * Navigation 각 탭을 나누는 라벨에 대한 뷰모델
 *
 * @author Doohyun
 */
class NavigationPageLabelItemViewModel(var label: String) : BindAbleItem {

    override fun getLayoutRes(): Int = R.layout.item_navigation_page_label
}