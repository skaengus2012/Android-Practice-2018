package nlab.practice.issue30.page


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import nlab.practice.R

/**
 * User End 페이지 프래그먼트
 *
 * @author Doohyun
 */
class UserEndFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_end, container, false)
    }


}
