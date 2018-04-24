package nlab.practice.c2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_simple_view.*
import nlab.practice.R
import nlab.practice.util.resource.showToast

/**
 * View 단에 해당하는 프래그먼트 정의.
 *
 * @author ndh1002
 */
class SimpleViewFragment : Fragment() {

    // 뷰 인플레이트.
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        showToast("View 프래그먼트 재생성 중...")

        return  inflater.inflate(R.layout.fragment_simple_view, container, false)
    }

    /**
     * View 가 생성될 때, 데이터 부착.
     *
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (context as? DataConserveActivity)?.getMyDataVO() ?.let { bindData(it) }
    }

    /**
     * [myDataVO] 를 받아, 프래그먼트의 뷰를 반영한다.
     *
     * @param myDataVO
     */
    fun bindData(myDataVO: DataFragment.MyDataVO?) = myDataVO?.let {
        tvMemberSn.text = it.memberSn.toString()
        tvName.text = it.name
    }
}
