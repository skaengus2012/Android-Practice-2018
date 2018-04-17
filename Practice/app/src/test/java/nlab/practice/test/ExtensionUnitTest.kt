package nlab.practice.test

import nlab.practice.config.toMemberOrganizationVO
import nlab.practice.model.member.MemberVO
import nlab.practice.model.member.OrganizationVO
import org.junit.Before
import org.junit.Test

/**
 * 확장 함수 유닛 테스트 제작
 *
 * 참고 URL
 * -> https://github.com/skaengus2012/Android-Practice-2018/issues/6
 *
 * @author ndh1002
 */
class ExtensionUnitTest {

    private lateinit var member : MemberVO
    private lateinit var organization: OrganizationVO

    @Before
    fun initData() {
        member = MemberVO(memberSn = 1, name = "남두현")
        organization = OrganizationVO(1, 1, "네이버 뮤직 - 안드로이드")
    }

    /**
     * 확장함수 테스트.
     */
    @Test
    fun testExtensionFunction() {
        // 확장 함수 정의.
        // 해당 확장함수는 ExtensionConfig.kt 에 존재.
        member.toMemberOrganizationVO(organization).let { println(it) }
    }
}