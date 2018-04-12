package nlab.practice.model.member

/**
 * MemberVO 를 제공하는 DAO 제작
 *
 * object 클래스는 싱글톤임.
 *
 * @author ndh1002
 */
object MockMemberDAO {

    fun selectList() : List<MemberVO> = listOf(
            MemberVO(1, "강현지", "11110", "Female", "사원"),
            MemberVO(1, "남두현", "11111", "Male", "사원"),
            MemberVO(1, "유덕형", "11112", "Male", "사원"),
            MemberVO(1, "유형주", "11114", "Male", "사원")
    )
}