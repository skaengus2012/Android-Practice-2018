package nlab.practice.issue22

import android.os.SystemClock
import nlab.practice.common.CodeDefinition
import nlab.practice.issue22.model.User

/**
 * User 정보를 조회하는 Mock 서비스 정의.
 *
 * @author Doohyun
 */
object MockWebService {

    /**
     * [userId] 를 이용하여, User 정보 조회 쿼리
     *
     * - 딜레이 타임을 둠.
     *
     * @param userId
     */
    fun getUser(userId : String) : User {
        // 딜레이 타임 정의.
        SystemClock.sleep(1500)

        return User(userId,"남두현", 29, CodeDefinition.GENDER_FLAG.Male)
    }

}