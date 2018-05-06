package nlab.practice.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * 구성원 정보에 대한 테이블 정보 정의
 *
 * @author ndh1002
 */
@Entity(tableName = "Member")
data class Member(
        @PrimaryKey val memberSn : Int,
        val memberName : String,
        val genderFlag : String,
        val birthDay : Long,
        var email : String? = null,
        var photo : String? = null
)