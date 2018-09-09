package nlab.practice.util.databinding.adapterview

import android.databinding.BaseObservable

/**
 * BaseObservable 이 포함된 바인드 뷰모델
 *
 * @author Doohyun
 * @since 2018. 07. 13
 */
open abstract class ItemViewModel : BindAbleItem, BaseObservable()