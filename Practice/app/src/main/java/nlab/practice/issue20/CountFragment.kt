package nlab.practice.issue20


import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_count.*

import nlab.practice.R
import nlab.practice.util.resource.convertColor

/**
 * 프래그먼트 갯수를 표시하는 화면 정의.
 *
 * @author ndh1002
 */
class CountFragment : Fragment() {

    companion object {

        private const val ARGUMENT_COUNT = "ARGUMENT_COUNT"
        private const val ARGUMENT_TEXT_COLOR_RES = "ARGUMENT_TEXT_COLOR_RES"

        /**
         * [count] 와 [textColorRes] 값을 담은 fragment 생산.
         *
         * @param count
         * @param textColorRes
         * @return
         */
        fun Create(count : Int, @ColorRes textColorRes : Int) : CountFragment =
                CountFragment().apply { arguments = CreateBundle(count, textColorRes) }

        /**
         * [count] 와 [textColorRes] 값을 가진 번들 생산.
         *
         * @param count
         * @param textColorRes
         * @return
         */
        private fun CreateBundle(count : Int, @ColorRes textColorRes : Int) : Bundle = Bundle().apply {
            putInt(ARGUMENT_COUNT, count)
            putInt(ARGUMENT_TEXT_COLOR_RES, textColorRes)
        }
    }

    private var count : Int? = null
    @ColorRes private var textColorRes : Int? = null

    /**
     * 번들에서 fragment 에 바인딩 될 값을 세팅.
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 데이터 세팅.
        arguments?.let {
            count = it.getInt(ARGUMENT_COUNT)
            textColorRes = it.getInt(ARGUMENT_TEXT_COLOR_RES)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_count, container, false)


    /**
     * 데이터를 뷰에 바인드.
     *
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkNotNull(count, {"[에러] 카운트가 비어있음"})
        checkNotNull(textColorRes, {"[에러] 텍스트 컬러 값이 비어있음"})

        tvNumber?.let {
            it.text = count!!.toString()
            it.setTextColor(convertColor(textColorRes!!))
        }
    }
}
