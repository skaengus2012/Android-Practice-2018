package nlab.practice.util.databinding;

/**
 * 데이터 바인딩 유틸 정의
 *
 * @author Doohyun
 */
public final class BindingUtil {

    private BindingUtil(){}

    /**
     * LiveEvent call
     *
     * <pre>
     *     코틀린에서는 구현할 수 없는 방식
     *     데이터바인딩 xml 에서는 꼭 제네릭이 스킵된 상태로 해야 제대로 작동함
     * </pre>
     *
     * @param event
     */
    public static void call(LiveEvent event) {
        event.call();
    }
}
