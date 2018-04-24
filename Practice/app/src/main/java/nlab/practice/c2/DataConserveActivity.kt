package nlab.practice.c2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_data_conserve.*
import nlab.practice.R

/**
 * 데이터 보존 화면 정의
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/9
 *
 * @author ndh1002
 */
class DataConserveActivity : AppCompatActivity() {

    private var dataFragment : DataFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_conserve)

        btnChangedData.setOnClickListener({ changeMyDataVOTo_권순필() })
    }

    /**
     * 현재 저장된 MyDataVO 를 출력한다.
     *
     * @return
     */
    fun getMyDataVO() : DataFragment.MyDataVO? {
        if (dataFragment == null) {
            setDataFragment()
        }

        return dataFragment?.myData
    }

    /**
     * 프래그먼트 매니저로부터 저장된 프래그먼트를 세팅.
     *
     * 저장된 프래그먼트가 없다면 새로운 프래그먼트를 만들고 매니저에 등록한다.
     */
    private fun setDataFragment() {
        dataFragment = (supportFragmentManager.findFragmentByTag(DataFragment.GetTag()) as? DataFragment)
                ?: run { createNewDataFragmentAndRegisterFM() }
    }

    /**
     * 새로운 프래그먼트를 프래그먼트 매니저에 등록하고, 제작한 프래그먼트를 출력한다.
     *
     * @return
     */
    private fun createNewDataFragmentAndRegisterFM() : DataFragment {
        val fragment = DataFragment().apply { myData = createMyDataVOFor_남두현() }

        supportFragmentManager.beginTransaction()
                .add(fragment, DataFragment.GetTag())
                .commit()

        return fragment
    }

    /**
     * 데이터를 권순필 상태로 변경하고, 뷰를 반영한다.
     */
    private fun changeMyDataVOTo_권순필() {
        val changeTargetData = createMyDataVOFor_권순필()

        dataFragment?.myData = changeTargetData

        (viewFragment as? SimpleViewFragment)?.bindData(changeTargetData)
    }

    /**
     * 남두현 에 해당하는 정보를 생산
     *
     * @return
     */
    private fun createMyDataVOFor_남두현() : DataFragment.MyDataVO = DataFragment.MyDataVO(1, "남두현")

    /**
     * 권순필 에 해당하는 정보를 생산.
     *
     * @return
     */
    private fun createMyDataVOFor_권순필() : DataFragment.MyDataVO = DataFragment.MyDataVO(2, "권순필")
}
