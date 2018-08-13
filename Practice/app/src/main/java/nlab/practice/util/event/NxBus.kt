package nlab.practice.util.event

import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

/**
 * N's lab 식 Event Bus
 *
 * @author Doohyun
 * @since 2018. 08. 13
 */
object NxBus {

    inline fun <reified T> toObservable() : Observable<T> = NxBusHelper.publishRelay.ofType(T::class.java)

    inline fun <reified T> toObservable(tag: String) : Observable<T> =
            NxBusHelper.publishRelay.ofType(TagEvent::class.java)
                    .filter{it.tag == tag}
                    .map { it.data }
                    .ofType(T::class.java)

    fun <T> post(any : T) = NxBusHelper.publishRelay.accept(any)

    fun <T> post(tag : String, any: T) = NxBusHelper.publishRelay.accept(TagEvent(tag, any))

    fun hasObservers() : Boolean = NxBusHelper.publishRelay.hasObservers()

    /**
     * 태그가 들어왔을 때 이벤트 정의
     */
    data class TagEvent<T>(val tag: String, val data : T)
}

/**
 * Relay 를 가지는 Object
 *
 * inline 함수를 사용하기 때문에 Relay 가 public 이어야함  -> 다른 클래스로 위임
 */
object NxBusHelper {
    val publishRelay : Relay<Any> by lazy {
        val result : PublishRelay<Any> = PublishRelay.create()

        result.toSerialized()
    }
}