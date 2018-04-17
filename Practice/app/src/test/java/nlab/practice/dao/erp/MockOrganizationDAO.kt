package nlab.practice.dao.erp

import nlab.practice.model.erp.OrganizationVO

/**
 * OrganizationVO 를 제작하는 DAO 제작
 *
 * @author ndh1002
 */
object MockOrganizationDAO {

    /**
     * 네이버 뮤직 조직 조회
     */
    fun findByNaverMusicAndroid() : OrganizationVO =
            OrganizationVO(1, "네이버 뮤직 - 안드로이드")

    /**
     * 마이다스 웹솔루션 조회
     */
    fun findByMidasWebSolution() : OrganizationVO =
            OrganizationVO(2, "마이듯 웹솔루션 - 응용")

    /**
     * 조직목록 출력.
     */
    fun selectList() : List<OrganizationVO> = listOf(findByNaverMusicAndroid(), findByMidasWebSolution())
}