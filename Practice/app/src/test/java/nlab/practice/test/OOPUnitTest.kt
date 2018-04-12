package nlab.practice.test

import nlab.practice.model.duck.*
import nlab.practice.model.member.MemberVO
import nlab.practice.model.member.MockMemberDAO
import nlab.practice.model.strategy.*
import org.junit.Test

/**
 * 객체지향에 대한 테스트 코드 기술
 *
 * 참고 URL ->
 * https://github.com/skaengus2012/Android-Practice-2018/issues/2
 *
 * @author ndh1002
 */
class OOPUnitTest {

    /**
     * 클래스 사용에 대한 기본 활용 테스트.
     */
    @Test
    fun testUsingSimpleClass() {
        val mallardDuck = MallardDuck()
        val rubberDuck = RubberDuck()
        val customType1Duck = CustomType1WingDuck("broduck")
        val customType2Duck = CustomType2WingDuck("Kang")

        runDuckSimulator(mallardDuck)
        runDuckSimulator(rubberDuck)
        runDuckSimulator(customType1Duck)
        runDuckSimulator(customType2Duck)
    }

    /**
     * 오리 시뮬레이터 기능 정의.
     *
     * [duck] 을 이용한 여러 기능 수행..
     *
     * @param duck
     */
    private fun runDuckSimulator(duck : Duck) {
        println()
        println("${duck.javaClass.simpleName} 객체 시뮬레이션.")

        duck.swim()
        duck.quack()
        duck.fly()
        println()
    }

    /**
     * 봉합 클래스 및 열거형 테스트.
     *
     * 의존성 주입체의 타입 확인하길 권장.
     *
     * Runnable -> Sealed class
     * Heat -> Enum
     */
    @Test
    fun testSealedClassAndEnum() {

        CustomType1WingDuck("Doohyun")
                .apply {
                    // 의존성 주입.
                    runnable = Runnable.High()
                    heat = Heat.Miss
                }
                .executeDIBehavior()

        CustomType2WingDuck("Soldier")
                .apply {
                    // 의존성 주입.
                    runnable = Runnable.Normal()
                    heat = Heat.Critical
                }
                .executeDIBehavior()

        RubberDuck()
                .apply {
                    // 의존성 주입.
                    runnable = Runnable.Low()
                    heat = Heat.Normal
                }
                .executeDIBehavior()
    }

    /**
     * 데이터 클래스 테스트
     *
     * MemberVO 는 data class.
     */
    @Test
    fun testDataClass() {

        // 빌더 패턴 대체.
        // NonNull 필드는 무조건 초기화 시켜야함.
        val member = MemberVO(name = "Doohyun", memberSn = 1, genderFlag = "Male")

        // 데이터 복사.
        val copyMember = member.copy()
        val copySeparateMember = member.copy(name = "broduck", memberSn = 2)

        // 데이터 출력.
        printMember(member)
        printMember(copyMember)
        printMember(copySeparateMember)
    }

    /**
     * [member] 를 받아 콘솔에 출력
     *
     * @param member
     */
    private fun printMember(member : MemberVO)
            = println("${member.memberSn}, ${member.name}, ${member.genderFlag}")

    /**
     * Object 클래스에 대한 테스트
     *
     * MockMemberDAO 는 object(싱글톤).
     */
    @Test
    fun testObjectClass() {
        MockMemberDAO.selectList().let {
            for (member in it) {
                printMember(member)
            }
        }
    }

    /**
     * Delegate pattern 에 대한 테스트.
     */
    @Test
    fun testDelegatePattern() = Delegate(FlyWithWing(), JumpAbleImpl()).let {
        // 대리자가 수행할 메소드 호출.
        it.fly()
        it.jump()
    }
}