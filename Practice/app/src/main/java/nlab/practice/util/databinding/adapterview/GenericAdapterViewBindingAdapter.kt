package nlab.practice.util.databinding.adapterview

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView

/**
 * Recycler View 관련 BindingAdapter
 *
 * 파라미터 목록은 value 순서 따라 정의해줘야함
 *
 * @param recyclerView
 * @param items
 * @param config
 */
@BindingAdapter(value = ["bindAdapterViewItems", "bindHeaderViewItem", "bindAdapterViewConfig"], requireAll = false)
fun <T : BindAbleItem> setItems(recyclerView: RecyclerView, items: List<T>?, headerViewModel: T?, config: GenericRecyclerViewConfig?) {
    // 설정 처리
    bindAdapterViewConfig(recyclerView, config)
    recyclerView.takeIf { it.adapter == null }?.let { it.adapter = GenericBindingRecyclerAdapter<T>() }

    // 아이템 세팅
    bindAdapterViewItems(recyclerView, items)
    bindHeaderViewItem(recyclerView, headerViewModel)

    // 데이터 업데이트
    recyclerView.adapter.notifyDataSetChanged()
}

/**
 * 제네릭 어댑터에 HeaderView 바인딩
 */
fun <T : BindAbleItem> bindHeaderViewItem(recyclerView: RecyclerView, headerViewItem : T?) = headerViewItem?.let {
    safeHeaderViewItem
    ->
    recyclerView.adapter
            ?.let { it as? GenericBindingRecyclerAdapter<T> }
            ?.run { this.headerViewItem = safeHeaderViewItem }
}

/**
 * 제네릭 어댑터에 아이템 목록 바인딩
 */
fun <T : BindAbleItem> bindAdapterViewItems(recyclerView: RecyclerView, items: List<T>?) = items?.let {
    safeItems
    ->
    recyclerView.adapter
            ?.let { it as? GenericBindingRecyclerAdapter<T> }
            ?.run {
                // 목록 정보 세팅
                with(this.items) {
                    clear()
                    addAll(safeItems)
                }
            }
}

/**
 * [recyclerView] 에 [config] 를 설정하고, 어댑터가 존재하지 않는다면 GenericBindingRecyclerAdapter 를 삽입한다.
 *
 * @param recyclerView
 * @param config
 */
fun bindAdapterViewConfig(recyclerView: RecyclerView, config: GenericRecyclerViewConfig?) {
    config?.let {
        // todo 설정 파일 세팅하기
    }
}