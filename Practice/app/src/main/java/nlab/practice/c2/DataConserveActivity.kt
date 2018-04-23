package nlab.practice.c2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import nlab.practice.R

/**
 * 데이터 보존 화면 정의
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/9
 *
 * @author ndh1002
 */
class DataConserveActivity : AppCompatActivity() {

    var dataFragment : DataFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_conserve)

        // 데이터 프래그먼트 초기화.
        setDataFragment()
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
        val fragment = DataFragment().apply { myData = createDoohuynMyData() }


        supportFragmentManager.beginTransaction()
                .add(fragment, DataFragment.GetTag())
                .commit()

        return fragment
    }

    /**
     * Doohyun 에 해당하는 정보를 생산
     *
     * @return
     */
    private fun createDoohuynMyData() : DataFragment.MyDataVO = DataFragment.MyDataVO(1, "Doohyun")
}
