package nlab.practice.util.resource

import nlab.practice.R
import nlab.practice.common.CodeDefinition

/**
 * [genderFlag] 를 이용하여, 성별 라벨을 출력.
 *
 * @param genderFlag
 * @return
 */
fun convertGenderLabel(genderFlag : String?) : String? = genderFlag
        ?.takeIf { it in arrayOf(CodeDefinition.GENDER_FLAG.Male, CodeDefinition.GENDER_FLAG.FeMale) }
        ?.run {
            when (this) {
                CodeDefinition.GENDER_FLAG.Male -> convertString(R.string.label_aac_male)
                CodeDefinition.GENDER_FLAG.FeMale -> convertString(R.string.label_aac_female)
                else -> null
            }
        }