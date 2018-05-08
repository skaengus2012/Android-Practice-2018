package nlab.practice.common.dao

import android.arch.persistence.room.*
import nlab.practice.common.model.User

@Dao
interface UserDao {

    /**
     * [user] 를 저장
     *
     * @param user
     */
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(user : User)

    /**
     * [user] 업데이트.
     *
     * @param user
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(user : User)

    /**
     * [userId] 로 데이터를 조회
     *
     * @param userId
     * @return
     */
    @Query("SELECT * FROM user WHERE userId = :userId")
    fun find(userId : String) : User?

    /**
     * [userId] 키를 가진 데이터의 개수를 조회한다.
     *
     * @param userId
     *
     */
    @Query("SELECT COUNT(*) FROM user WHERE userId = :userId")
    fun getCount(userId : String) : Int
}