package nlab.practice.issue30

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import nlab.practice.R
import nlab.practice.common.api.mock.MockUserWebService
import nlab.practice.common.model.User
import nlab.practice.issue30.page.UserFragment
import java.util.*

/**
 * Single Activity - Multi fragment 를 위한 Navigation
 *
 * @author Doohyun
 */
class NavigationActivity : AppCompatActivity(), UserFragment.UserSupplier{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
    }

    /**
     * 유저정보를 조회 후, 랜덤하게 데이터 하나를 뽑는다.
     *
     * @return
     */
    override fun getUser(): User {
        val users = MockUserWebService.getUsers()
                .apply { Collections.shuffle(this) }

        return users[0]
    }

    override fun goToUser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
