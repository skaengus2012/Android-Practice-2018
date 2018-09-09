package nlab.practice.issue33.fragment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import nlab.practice.R

const val SHARED_ELEMENT_FRAGMENT_IMAGE_URL = "http://img1.daumcdn.net/thumb/R1024x0/?fname=http://i1.daumcdn.net/cfile255/image/25065E3A57A215D718BC5E"

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
