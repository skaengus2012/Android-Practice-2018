package nlab.practice.model.member

/**
 * 조직을 표현한 정보 정의
 *
 * @author ndh1002
 */
data class OrganizationVO(
        var memberSn : Int,
        var organizationSn : Int,
        var organizationName : String
)