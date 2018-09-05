package nlab.practice.util.databinding.adapterview

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView

/**
 * Recycler View 관련 BindingAdapter
 *
 * @param recyclerView
 * @param items
 * @param config
 */
@BindingAdapter(value = ["bindAdapterViewItems", "bindAdapterViewConfig"], requireAll = false)
fun <T : BindAbleItem> setItems(recyclerView: RecyclerView, items: List<T>?, config: GenericRecyclerViewConfig?) {
    // 설정 처리
    bindAdapterViewConfig(recyclerView, config)
    recyclerView.takeIf { it.adapter == null }?.let { it.adapter = GenericBindingRecyclerAdapter<T>() }

    // 아이템 세팅
    bindAdapterViewItems(recyclerView, items)
}

/**
 * 제네릭 어댑터 관련 바인딩 어댑터
 *
 * @author Doohyun
 * @since 2018. 07. 13
 */
fun <T : BindAbleItem> bindAdapterViewItems(recyclerView: RecyclerView, items: List<T>?) = items?.let {
    recyclerView.adapter
            ?.let { it as? GenericBindingRecyclerAdapter<T> }
            ?.run {
                // 목록 정보 세팅
                with(this.items) {
                    clear()
                    addAll(items)
                }

                this.notifyDataSetChanged()
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