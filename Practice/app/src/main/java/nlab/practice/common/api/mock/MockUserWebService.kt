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
        SystemClock.sleep(1000)

        return User(userId, "Suzy", 29, CodeDefinition.GENDER_FLAG.FeMale, "http://ilyo.co.kr/contents/article/images/2017/1115/1510756193564472.jpg")
    }

    /**
     * 저장된 유저정보를 조회한다.
     *
     * @return
     */
    fun getUsers() : List<User> {
        SystemClock.sleep(1500)

        return listOf(
                User("N", "Doohyun", 29, CodeDefinition.GENDER_FLAG.Male, "https://pbs.twimg.com/profile_images/2297754865/bm1u7mg20v8t6wywyqnn_400x400.jpeg"),
                User("U", "broduck", 28, CodeDefinition.GENDER_FLAG.Male, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSgviwaJ9UXrJn7Wl7TeLeogh6V2A7R7sHWSb81k1jXXgd9Z0cdqQ"),
                User("K", "Hyunji", 26, CodeDefinition.GENDER_FLAG.FeMale, "http://mblogthumb1.phinf.naver.net/20160203_20/papero2_1454475327245xUJXD_JPEG/%BA%ED%B7%A2%C0%A7%B5%B5%BF%EC.jpg?type=w2"),
                User("S", "lhm7877", 34, CodeDefinition.GENDER_FLAG.Male, "https://i.ytimg.com/vi/HXpgp4eeelE/maxresdefault.jpg")
        )

    }
}