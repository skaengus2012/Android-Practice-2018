package nlab.practice.model.member

/**
 * 구성원 정보 (Value Object) 정의
 *
 * @author ndh1002
 */
data class MemberVO(
        var memberSn : Int,
        var name : String,
        var employeeId : String? = null,
        var genderFlag : String? = null,
        var position : String? = null
)