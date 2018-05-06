package nlab.practice.db.model.erp

/**
 * 구성원 정보와 조직 정보를 합친 정보.
 *
 * @author ndh1002
 */
data class MemberOrganizationVO(
        var memberSn : Int,
        var organizationSn : Int,
        var memberName : String,
        var organizationName : String
)