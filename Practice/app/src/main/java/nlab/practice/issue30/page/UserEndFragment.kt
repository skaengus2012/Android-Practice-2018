package nlab.practice.issue30.page


import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user_end.view.*
import nlab.practice.PracticeApplication
import nlab.practice.R

import nlab.practice.databinding.FragmentUserEndBinding

private const val PARAM_USER_ID = "paramUserId"

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

        fun create(userId : String) : UserEndFragment = UserEndFragment().apply {
            arguments = Bundle().apply {
                putString(PARAM_USER_ID, userId)
            }
        }
    }

    private lateinit var _binding : FragmentUserEndBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_end, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(PARAM_USER_ID)?.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.ivProfile.transitionName = createProfileTransitionName(this)
            }
        }
    }
}
