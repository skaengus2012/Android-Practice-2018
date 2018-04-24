package nlab.practice.issue9

import android.os.Bundle
import android.support.v4.app.Fragment


/**
 * 데이터 보존용 프래그먼트 정의
 *
 * @author ndh1002
 */
class DataFragment : Fragment() {

    companion object {
        fun GetTag() : String = DataFragment::class.java.simpleName
    }

    var myData : MyDataVO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 데이터 보존하겠다는 처리.
        // 객체를 유지한다.
        retainInstance = true
    }

    /**
     * 데이터 저장 정보 정의
     */
    data class MyDataVO (val memberSn : Int, val name : String)
}