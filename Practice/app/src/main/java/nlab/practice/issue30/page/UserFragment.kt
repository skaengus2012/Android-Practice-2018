package nlab.practice.issue30.page


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nlab.practice.PracticeApplication
import nlab.practice.R

import nlab.practice.databinding.FragmentUserBinding
import nlab.practice.util.view.NavigationController

/**
 * User 의 데이터를 표시하는 프래그먼트 정의
 *
 * @author Doohyun
 */
class UserFragment : Fragment() {

    private lateinit var _viewModel : UserViewModel

    private val _navigationController : NavigationController by lazy {
        NavigationController(R.id.layoutFragment, childFragmentManager)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentUserBinding.inflate(inflater, container, false)
        _viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        binding.viewModel = _viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel.initUser()
        _viewModel.goToUserEndEvent.observe(this, Observer {
            userItem
            ->
        })
    }
}
