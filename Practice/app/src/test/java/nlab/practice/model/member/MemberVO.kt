package nlab.practice.model.member

/**
 * 구성원 정보 (Value Object) 정의
 *
 * data 키워드를 붙이면, VO 로써의 기능을 이용할 수 있음.
 * -> ex. 빌더, 깊은 복사.
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