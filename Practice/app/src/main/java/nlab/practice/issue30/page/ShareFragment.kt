package nlab.practice.issue30.page


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import nlab.practice.R

/**
 * 공유 화면 정의
 *
 * @author Doohyun
 */
class ShareFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share, container, false)
    }
}
