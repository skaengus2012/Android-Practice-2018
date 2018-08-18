package nlab.practice.common.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * 유저 정보를 담은 정보 정의
 *
 * @author Doohyun
 */
@Entity
data class User(
        @PrimaryKey val userId : String,
        var name : String,
        var age : Int,
        var genderFlag : String) {

    fun getAgeToString() : String = age.toString()
}