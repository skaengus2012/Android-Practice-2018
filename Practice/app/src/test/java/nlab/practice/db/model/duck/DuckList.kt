package nlab.practice.db.model.duck

/**
 * Duck 종류만 담을 수 있는 목록 클래스 제작.
 *
 * @author ndh1002
 */
class DuckList<T> where T : Duck {
    // <T> DuckList<T extends Duck> 과 동일 -> where 키워드는 자바 제네릭 타입 방식과 대치.

    // 초기화 지연 키워드.
    // 이 변수는 Non-Null 이지만, 초기화에 지연에 있어 뒤로 미루겠다는 역할.
    lateinit var ducks : List<T>

    /**
     * [list] 를 [ducks] 에 주입한다.
     *
     * @param list
     */
    fun injectLists(list : List<T>) {
        this.ducks = list
    }
}