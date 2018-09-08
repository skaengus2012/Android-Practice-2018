package nlab.practice.issue33.fragment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import nlab.practice.R

/**
 * 프래그먼트 간 이동에서 Shared Element 를 사용하는 예제 제시
 *
 * @author Doohyun
 */
class SharedElementFragmentToFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_element_fragment_to_fragment)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.layoutFragment, SE1Fragment())
                    .commit()
        }
    }
}
