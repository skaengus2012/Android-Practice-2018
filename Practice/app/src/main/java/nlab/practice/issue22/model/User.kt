package nlab.practice.issue22.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import nlab.practice.R
import nlab.practice.common.CodeDefinition
import nlab.practice.util.resource.convertString

/**
 * 유저 정보를 담은 정보 정의
 *
 * @author Doohyun
 */
@Entity
data class User(
        @PrimaryKey
        val userId : String,
        var name : String,
        var age : Int,
        var genderFlag : String) {

        /**
         * 코드 값을 바탕으로 코드에 따른 라벨을 출력한다.
         *
         * @return
         */
        fun getGenderString() : String = if (genderFlag == CodeDefinition.GENDER_FLAG.Male) {
                convertString(R.string.label_aac_male)
        } else {
                convertString(R.string.label_aac_female)
        }
}