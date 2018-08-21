package nlab.practice.common.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import nlab.practice.R
import nlab.practice.util.resource.convertGenderLabel
import nlab.practice.util.resource.convertString

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

    /**
     * 나이를 라벨로 변환
     *
     * @return
     */
    fun toAgeLabel() : String = convertString(R.string.format_aac_age)
            .let { String.format(it, age) }

    /**
     * 코드를 라벨로 변환
     *
     * @return
     */
    fun toGenderLabel() : String = convertGenderLabel(genderFlag)!!
}