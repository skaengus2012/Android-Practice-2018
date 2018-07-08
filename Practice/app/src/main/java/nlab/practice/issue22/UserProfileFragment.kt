package nlab.practice.issue22


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user_profile.*

import nlab.practice.R
import nlab.practice.databinding.FragmentUserProfileBinding

/**
 * 안드로이드 아키텍처 컴포넌트 연습을 위한 프래그먼트.
 *
 * @author Doohyun
 */
class UserProfileFragment : Fragment() {

    companion object {
        private const val ARGUMENT_USER_ID : String = "ARGUMENT_USER_ID"

        /**
         * [userId] 를 담은 프래그먼트 생산.
         *
         * @param userId
         */
        fun newInstance(userId : String) : UserProfileFragment =
                Bundle().apply { putString(ARGUMENT_USER_ID, userId) }
                        .let { UserProfileFragment().apply { arguments = it } }
    }

    // 뷰모델 정의.
    private lateinit var viewModel : UserProfileViewModel
    private lateinit var dataBinding : FragmentUserProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        dataBinding =
                FragmentUserProfileBinding.inflate(inflater, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnUpdateName.setOnClickListener { updateName() }
        btnRefresh.setOnClickListener{ viewModel.refreshUser() }
    }

    /**
     * ViewModel 생성.
     *
     * @param savedInstanceState
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel::class.java)
                .apply { userId = arguments?.getString(ARGUMENT_USER_ID) }

        viewModel.user?.observe(this, Observer {
            it?.let {
                Log.d(UserProfileFragment::class.java.simpleName, "[정보] 뷰 업데이트.")

                dataBinding.user = it
            }
        })
    }

    /**
     * 이름 정보 업데이트.
     */
    private fun updateName() {
        val name = tvName.text.toString()

        viewModel.updateName(name)
    }
}
