package nlab.practice.issue22


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user_profile.*

import nlab.practice.R
import nlab.practice.common.CodeDefinition
import nlab.practice.issue22.viewModel.UserProfileViewModel
import nlab.practice.util.resource.convertString

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
        fun create(userId : String) : UserProfileFragment =
                Bundle().apply { putString(ARGUMENT_USER_ID, userId) }
                        .let { UserProfileFragment().apply { arguments = it } }
    }

    // 뷰모델 정의.
    private lateinit var viewModel : UserProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
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
                tvName.text = it.name
                tvAge.text = it.age.let { age -> String.format(convertString(R.string.format_aac_age), age) }
                tvGender.text = if (it.genderFlag == CodeDefinition.GENDER_FLAG.Male) {
                    convertString(R.string.label_aac_male)
                } else {
                    convertString(R.string.label_aac_female)
                }
            }
        })
    }
}
