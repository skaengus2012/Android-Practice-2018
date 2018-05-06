package nlab.practice.db.model.operatorTest

/**
 * 연산자 오버로딩을 구현할 수 있는 클래스 정의 (operator keyword)
 *
 * 연산자 오버로딩 공식 주소 ->
 *      https://kotlinlang.org/docs/reference/operator-overloading.html
 *
 * @author ndh1002
 */
class OperatorSupportList<T> {

    private val items : MutableList<T>

    init {
        items = ArrayList()
    }

    /**
     * [item] 을 추가.
     *
     * @param item
     */
    fun add(item : T) = items.add(item)

    override fun toString(): String = items.toString()

    /**
     * + 연산자 오버로딩
     *
     * 현재의 목록과 [items] 을 추가한 목록을 출력한다.
     *
     * @param items
     */
    operator fun plus(items : OperatorSupportList<T>) : OperatorSupportList<T> {
        val currentItems = this.items
        val targetItems = items.items

        println("+ 연산 실행.")

        return OperatorSupportList<T>().apply {
            this.items.addAll(currentItems)
            this.items.addAll(targetItems)
        }
    }

    /**
     * += 연산자 오버로딩.
     *
     * 현재의 목록과 [items] 을 추가한 목록을 출력한다.
     *
     * @param items
     */
    operator fun plusAssign(items: OperatorSupportList<T>) {
        println("+= 연산 실행.")

        this.items.addAll(items.items)
    }

    /**
     * [items] 에 있는 데이터를 제거.
     *
     * @param items
     */
    infix fun minus(items: OperatorSupportList<T>) = this.items.removeAll(items.items)
}