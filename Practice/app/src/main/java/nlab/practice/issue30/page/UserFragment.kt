package nlab.practice.issue30.page


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import nlab.practice.R
import nlab.practice.common.model.User


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * User 의 데이터를 표시하는 프래그먼트 정의
 *
 * @author Doohyun
 */
class UserFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }


    interface UserSupplier {
        fun getUser() : User

        fun goToUser()
    }
}
