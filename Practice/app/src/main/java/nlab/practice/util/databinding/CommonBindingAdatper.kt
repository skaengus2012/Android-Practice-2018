package nlab.practice.util.databinding

import android.databinding.BindingAdapter
import android.widget.ImageView
import nlab.practice.PracticeApplication
import nlab.practice.R
import nlab.practice.util.GlideApp

/**
 * 어느 곳에서나 사용하는 Binding Adapter 부분 정의
 *
 * @author Doohyun
 * @since 2018. 09. 03
 */

/**
 * [view] 에 [profileUrl] 에 해당하는 사진 첨부
 */
@BindingAdapter("bindProfileCircleCrop")
fun bindProfileCircleCrop(view: ImageView, profileUrl: String?) =
        GlideApp.with(PracticeApplication.getContext())
                .load(profileUrl)
                .placeholder(R.drawable.common_img_user_circle)
                .circleCrop()
                .override(100, 100)
                .into(view)

/**
 * [view] 에 [profileUrl] 에 해당하는 사진 첨부
 */
@BindingAdapter("bindProfile")
fun bindProfile(view: ImageView, profileUrl: String?) =
        GlideApp.with(PracticeApplication.getContext())
                .load(profileUrl)
                .placeholder(R.drawable.common_img_user_circle)
                .centerCrop()
                .into(view)