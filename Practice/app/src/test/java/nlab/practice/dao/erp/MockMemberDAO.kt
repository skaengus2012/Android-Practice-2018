package nlab.practice.dao.erp

import nlab.practice.config.CodeDefinition
import nlab.practice.model.erp.MemberVO

/**
 * MemberVO 를 제공하는 DAO 제작
 *
 * object 클래스는 싱글톤임.
 *
 * @author ndh1002
 */
object MockMemberDAO {

    /**
     * 구성원 목록을 출력.
     */
    fun selectList() : List<MemberVO> = listOf(
            MemberVO(1, "강현지", "11110", CodeDefinition.GENDER_FLAG.FeMale, "사원", organizationSn = 2),
            MemberVO(2, "남두현", "11111", CodeDefinition.GENDER_FLAG.Male, "사원", organizationSn = 1),
            MemberVO(3, "유덕형", "11112", CodeDefinition.GENDER_FLAG.Male, "사원", organizationSn = 2),
            MemberVO(4, "유형주", "11114", CodeDefinition.GENDER_FLAG.Male, "사원", organizationSn = 2)
    )
}