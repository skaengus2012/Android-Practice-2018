package nlab.practice.issue22

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import nlab.practice.R

/**
 * 안드로이드 아키텍처 컴포너트 예제
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/22
 *
 * @author ndh1002
 */
class UserActivity : AppCompatActivity() {

    private var userProfileFragment : UserProfileFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        attachFragment("ndh1002")
    }

    /**
     * 프래그먼트 부착
     *
     * @param userId
     */
    private fun attachFragment(userId : String) {
        userProfileFragment = UserProfileFragment.create(userId)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.layoutFragment, userProfileFragment)
                .commit()
    }
}

