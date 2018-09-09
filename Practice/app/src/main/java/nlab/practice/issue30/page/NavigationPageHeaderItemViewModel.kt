package nlab.practice.issue30.page

import nlab.practice.R
import nlab.practice.util.databinding.adapterview.BindAbleItem

/**
 * 네비게이션 헤더 관련 뷰모델
 *
 * @author Doohyun
 */
class NavigationPageHeaderItemViewModel(var title: String) : BindAbleItem {

    override fun getLayoutRes(): Int = R.layout.item_navigation_page_header
}