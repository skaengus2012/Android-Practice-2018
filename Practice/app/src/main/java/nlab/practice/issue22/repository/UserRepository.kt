package nlab.practice.issue22.repository

import Ndroid.appFactory.util.business.AsyncBuilder
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nlab.practice.issue22.MockWebService
import nlab.practice.issue22.model.User
import nlab.practice.issue22.viewModel.UserLive
import nlab.practice.issue22.viewModel.UserMutableLive

/**
 * User 정보를 조회하고, ViewModel 에 업데이트하는 클래스 레벨 정의.
 *
 * @author Doohyun
 */
object UserRepository {

    /**
     * [userId] 를 이용하여, 데이터를 조회하는 함수 정의
     *
     * @param userId
     * @return
     */
    fun getUser(userId : String) : UserLive {
        val result = UserMutableLive()

        Observable.create<User> {
                    val user = MockWebService.getUser(userId)
                    it.onNext(user)

                    Log.e("sad", "adsd")

                    it.onComplete()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result.value = it })

        return result
    }


}