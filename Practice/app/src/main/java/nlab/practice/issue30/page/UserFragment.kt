package nlab.practice.issue30.page


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import nlab.practice.databinding.FragmentUserBinding
import nlab.practice.issue30.getActivityNavigationController
import nlab.practice.util.V4Pair
import nlab.practice.util.view.SharedElementFragmentSupportable
import nlab.practice.util.view.getDefaultNavigationTagName

/**
 * User 의 데이터를 표시하는 프래그먼트 정의
 *
 * @author Doohyun
 */
class UserFragment : Fragment() {

    private var _binding : FragmentUserBinding? = null
    private var _viewModel : UserViewModel? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (_binding == null || _viewModel == null) {
            _binding = FragmentUserBinding.inflate(LayoutInflater.from(context), container, false)
            _viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

            _binding?.viewModel = _viewModel
        }

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel?.initUser()

        _viewModel?.goToUserEndEvent?.observe(this, Observer {
            pair
            ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)  {
                getActivityNavigationController()
                        ?.replaceWithSharedElement(
                                getDefaultNavigationTagName<UserFragment>(),
                                object : SharedElementFragmentSupportable {
                                    override fun getSharedElementPairs(): List<V4Pair<out View, String>> = listOf(
                                            V4Pair(pair?.first, pair?.second?.userId?.let { UserEndFragment.createProfileTransitionName(it) })
                                    )
                                }
                        ) {
                            UserEndFragment.create(pair?.second!!)
                        }
            }
        })
    }
}
