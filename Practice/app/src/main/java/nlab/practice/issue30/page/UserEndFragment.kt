package nlab.practice.issue30.page


import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.fragment_user_end.view.*
import nlab.practice.common.model.User

import nlab.practice.databinding.FragmentUserEndBinding
import nlab.practice.util.GlideApp

/**
 * User End 페이지 프래그먼트
 *
 * @author Doohyun
 */
class UserEndFragment : Fragment() {

    companion object {

        /**
         * Profile 의 Shared Element transitionName 을 출력한다.
         *
         * @param userId TransitionName 에 사용할 아이디
         * @return TransitionName 출력
         */
        fun createProfileTransitionName(userId : String) : String = "USER_END_FRAGMENT_$userId"

        fun create(user : User) : UserEndFragment = UserEndFragment().apply {
            _user = user
        }
    }

    private lateinit var _user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
            sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.no_transition)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var binding =
                FragmentUserEndBinding.inflate(LayoutInflater.from(context), container, false)

        _user.run { binding.user = this }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.ivProfile.transitionName = createProfileTransitionName(_user.userId)
        }

        postponeEnterTransition()
        GlideApp.with(this)
                .load(_user.profile)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(object: RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }
                })
                .into(view.ivProfile)
    }
}
