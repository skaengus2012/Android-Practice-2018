package nlab.practice.test

import nlab.practice.common.CodeDefinition
import nlab.practice.config.toMemberOrganizationVO
import nlab.practice.dao.erp.MockMemberDAO
import nlab.practice.dao.erp.MockOrganizationDAO
import nlab.practice.db.model.erp.MemberVO
import nlab.practice.db.model.erp.OrganizationVO
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * 함수형 관련 유닛 테스트 정의
 *
 * 참고 URL
 *  -> https://github.com/skaengus2012/Android-Practice-2018/issues/5
 *
 * @author ndh1002
 */
class FunctionalUnitTest {

    private lateinit var members : List<MemberVO>
    private lateinit var organizations: List<OrganizationVO>

    /**
     * 구성원 정보 초기화.
     */
    @Before
    fun initData() {
        members = MockMemberDAO.selectList()
        organizations = MockOrganizationDAO.selectList()
    }

    /**
     * 남자가 몇명인지 계산하는 함수 제작.
     */
    @Test
    fun testCountMale() {
        members.filter { it.genderFlag == CodeDefinition.GENDER_FLAG.Male }.count().let { println("구성원 중, 남자는 ${it}명") }
    }

    /**
     * 여자 구성원 이름 출력.
     */
    @Test
    fun testNameFeMale() {
        members.filter { it.genderFlag == CodeDefinition.GENDER_FLAG.FeMale }.map { it.name }.forEach { println(it) }
    }

    /**
     * 구성원 정보와 조직 정보를 합친 정보 출력.
     */
    @Test
    fun testCreateMemberOrganizationVO() {
        members.flatMap {
            member ->
                organizations
                        .filter { it.organizationSn == member.organizationSn }
                        .map { member.toMemberOrganizationVO(it) }
        }.forEach { println(it) }
    }

    /**
     * 맵을 이쁘게 출력할 수 있는 확장함수 추가.
     */
    private fun <T, U> Map<T, U>.prettyPrintGroup() {
        keys.forEach{ println("$it = ${get(it)}") }
    }

    /**
     * 구성원 순번으로 그룹핑.
     */
    @Test
    fun testAssociateByMemberSn() = members.associateBy { it.memberSn }.prettyPrintGroup()

    /**
     * 구성원 순번을 key, 이름을 value 로 그룹핑.
     */
    @Test
    fun testAssociateByMemberSn_Name() = members.associateBy({it.memberSn}, {it.name}).prettyPrintGroup()

    /**
     * 조직종류순번으로 맵 제작.
     *
     * - 중복키로 했을 때, 에러가 발생하는가? (발생하지 않음)
     */
    @Test
    fun testDuplicateAssociateByOrganization() = members.associateBy({it.organizationSn}).prettyPrintGroup()

    /**
     * 그룹핑한 결과를 특정 알고리즘을 가진 맵에 추가
     */
    @Test
    fun testAssociateByToMemberSn() {

        // 순서가 없는 구성원 목록.
        val shuffledMembers = members.shuffled()
        shuffledMembers.map { it.name }.let { println("랜덤 배치된 구성원 이름 : -> $it") }
        println()

        // LinkedHashMap 으로 구성원 목록 정의.
        // LinkedHashMap 의 경우, 입력된 순서로 데이터 유지가 됨.
        printAssociateByToResult(shuffledMembers, LinkedHashMap())

        // TreeMap 으로 구성원 목록 정의.
        // TreeMap 의 경우, key 에 의한 정렬 순서로 데이터가 유지됨.
        printAssociateByToResult(shuffledMembers, TreeMap())
    }

    /**
     * [members] 의 내용을 [emptyGroup] 에 담고 프린트.
     *
     * @param members
     * @param emptyGroup
     */
    private fun printAssociateByToResult(members : List<MemberVO>, emptyGroup : MutableMap<Int, String>) {
        println("[${emptyGroup::class.java.simpleName}] 에 담기")
        members.associateByTo(emptyGroup, {it.memberSn}, {it.name}).prettyPrintGroup()
        println()
    }

    /**
     * 조직 순번으로 그룹핑.
     */
    @Test
    fun testGroupByOrganization() = members.groupBy { it.organizationSn }.prettyPrintGroup()


    /**
     * 조직 순번을 key, 이름을 value 로 그룹핑.
     */
    @Test
    fun testGroupByOrganization_Name() = members.groupBy({it.organizationSn}, {it.name}).prettyPrintGroup()

    /**
     * GroupingBy 테스트.
     *
     * Grouping 된 상태에 대하여, 유틸 함수를 사용할 수 있음.
     */
    @Test
    fun testGroupingBy() {

        // 성별에 따른 카운트
        members.groupingBy { it.genderFlag }.eachCount().prettyPrintGroup()
        println()

        // 조직 순번에 대하여, 구성원 이름 목록을 출력한다.
        members.groupingBy { it.organizationSn }
                .fold("") { value, memberVo -> value + "${memberVo.name} "}
                .prettyPrintGroup()
        println()

        // key 에 대하여, 구성원 이름을 누적시킨 정보를 제작한다.
        // 억지로 예시를 만들다 보니 다음과 같이 처리 됨.
        members.groupingBy { it.organizationSn }
                .reduce { _, accumulator, element ->
                    val currentElementName = element.name
                    val accumulatorElementName = accumulator.name

                    accumulator.copy(name = "$accumulatorElementName $currentElementName")
                }.prettyPrintGroup()
    }
}