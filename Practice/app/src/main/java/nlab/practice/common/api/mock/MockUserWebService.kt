package nlab.practice.common.api.mock

import android.os.SystemClock
import nlab.practice.common.CodeDefinition
import nlab.practice.common.model.User

/**
 * User 정보를 조회하는 Mock 서비스 정의.
 *
 * @author Doohyun
 */
object MockUserWebService {

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

        return User(userId, "남두현", 29, CodeDefinition.GENDER_FLAG.Male)
    }

    /**
     * 저장된 유저정보를 조회한다.
     *
     * @return
     */
    fun getUsers() : List<User> {
        SystemClock.sleep(1500)

        return listOf(
                User("N", "Doohyun", 29, CodeDefinition.GENDER_FLAG.Male),
                User("K", "Hyunji", 26, CodeDefinition.GENDER_FLAG.FeMale),
                User("U", "broduck", 28, CodeDefinition.GENDER_FLAG.Male),
                User("S", "sjyoon", 34, CodeDefinition.GENDER_FLAG.Male)
        )

    }
}