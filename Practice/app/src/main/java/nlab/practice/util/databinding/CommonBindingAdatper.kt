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
@BindingAdapter("setProfile")
fun setProfile(view: ImageView, profileUrl: String?) =
        GlideApp.with(PracticeApplication.getContext())
                .load(profileUrl)
                .placeholder(R.drawable.ic_face_black_24dp)
                .centerCrop().override(200, 200)
                .into(view)