package nlab.practice.db.dao

import android.arch.persistence.room.*
import nlab.practice.db.model.Member

/**
 * 구성원 정보에 대한 Dao 정의
 *
 * @author ndh1002
 */
@Dao
interface MemberDao {

    /**
     * [member] 를 저장.
     *
     * @param member
     */
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(member : Member)

    /**
     * [member] 정보를 업데이트.
     *
     * @param member
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(member : Member)

    /**
     * [memberSn] 으로 구성원 정보 삭제.
     *
     * @param memberSn
     */
    @Query("DELETE FROM Member WHERE memberSn = :memberSn")
    fun deleteByMemberSn(memberSn : Int)

    /**
     * [memberSn] 으로 구성원 정보 조회
     *
     * @param memberSn
     * @return
     */
    @Query("SELECT * FROM member WHERE memberSn = :memberSn")
    fun find(memberSn : Int) : Member?
}