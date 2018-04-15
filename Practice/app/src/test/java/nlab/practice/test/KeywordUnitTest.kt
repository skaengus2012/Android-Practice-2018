package nlab.practice.test

import nlab.practice.model.duck.CustomType1WingDuck
import nlab.practice.model.duck.Duck
import nlab.practice.model.duck.NamedDuck
import nlab.practice.model.duck.RubberDuck
import org.junit.Test

typealias DuckList = MutableList<Duck>

/**
 * Kotlin keyword 관련 테스트 슈트 정의
 *
 * 참고 URL
 *  -> https://github.com/skaengus2012/Android-Practice-2018/issues/3
 *
 * @author ndh1002
 */
class KeywordUnitTest {

    /**
     * Type Casting 관련 테스트 제작.
     */
    @Test
    fun testTypeCast() {
        // Any Type 은 모든 타입의 최상위를 의미. -> 자바의 Object 와 유사.
        val number : Any = 5.5

        // as 키워드를 사용하여, 캐스팅을 할 수 있음.
        // 타입 변환 실패 시, 예외를 던짐. (Unsafe Type Cast)
        val numberDouble = number as Double
        println(numberDouble)

        // as? 키워드 역시, 타입 캐스팅. (Optional 현식으로 타입 캐스팅).
        // 타입 변환 실패 시, 없는 상태의 Optional 로 처리 (safe type Cast)
        val numberInt = numberDouble as? Int
        numberInt?.let { println(it) }
    }

    /**
     * in 키워드를 이용한 포함 여부.
     */
    @Test
    fun testTypeContains() {
        val numbers = listOf(1,2,3,4)

        // 포함 여부 테스트.
        assert(4 in numbers)

        // 미포함 여부 테스트.
        assert(5 !in numbers)

        println("테스트 성공!")
    }

    /**
     * in, out 을 이용한 wildcard 테스트.
     */
    @Test
    fun testGenericWildCard() {

        val namedDucks : MutableList<NamedDuck> = mutableListOf(
                CustomType1WingDuck("Doohyun`s Duck"),
                RubberDuck()
        )

        // in 을 이용한 쓰기 전용 함수 처리.
        addNewCustomDuck(namedDucks, "broduck")

        // out 을 이용한 읽기 전용 함수 정의.
        printDuckNames(namedDucks)

        // star projections 테스트.
        println("- star projections 테스트-")
        printItems(listOf(1,2,3,4))
        printItems(listOf("A", "B", "C", "D"))
    }

    /**
     * out keyword 를 이용한 제네릭 처리.
     *
     * <? extends T> 와 대치. -> 해당 키워드는 오직 목록에 대하여 읽기기준 수행만 가능.
     *
     * [ducks] 목록을 순회하며, 이름을 출력.
     *
     * @param ducks
     */
    private fun printDuckNames(ducks : List<out NamedDuck>) {
        println("- 오리 이름 출력 테스트 -")

        for (duck in ducks) {
            println(duck.name)
        }
    }

    /**
     * in keyword 를 이용한 제네릭 처리.
     *
     * <? super T> 와 대치 -> 해당 키워드는 오직 목록에 대하여 쓰기기준 수행만 가능.
     *
     * [ducks] 에 [newDuckName] 을 가진 오리를 추가한다.
     *
     * @param ducks
     * @param newDuckName
     */
    private fun addNewCustomDuck(ducks : MutableList<in NamedDuck>, newDuckName : String)
            = CustomType1WingDuck(newDuckName).let { ducks.add(it) }


    /**
     * Generics 사용 시, 모든 객체에 대한 허용 처리.
     *
     * <?> 와 대치.
     *
     * [items] 의 내용을 출력.
     *
     * @param items
     */
    private fun printItems(items : List<*>) = println(items)

    /**
     * 객체의 타입을 체크하는 기능 테스트.
     *
     * 자바의 instanceOf 와 대치.
     */
    @Test
    fun testInstanceTypeCheck() {

        val rubberDuck : Any = RubberDuck()

        assert(rubberDuck is NamedDuck)

        assert(rubberDuck !is CustomType1WingDuck)
    }

    /**
     * Type 명이 길 경우, typealias 키워드를 이용해서 단축시킬 수 있음.
     */
    @Test
    fun testTypeAlias() {
        // Duck list 에 데이터 추가.
        val ducks : DuckList = ArrayList()
        with(ducks) {
            add(CustomType1WingDuck("Doohyun`s Duck"))
            add(RubberDuck())
        }

        flyDuckList(ducks)
    }

    /**
     * [ducks] 내부의 오리들의 날기 동작 수행.
     *
     * @param ducks
     */
    private fun flyDuckList(ducks : DuckList) {
        for (duck in ducks) {
            duck.fly()
        }
    }
}