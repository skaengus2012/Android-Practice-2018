package nlab.practice.reactivetest

import nlab.practice.util.event.NxBus
import org.junit.Test

/**
 * Bus 관련 유닛 테스트 추가
 *
 * @author Doohyun
 * @since 2018. 08. 13
 */
class NxBusUnitTest {

    /**
     * Do simple test using NxBus
     */
    @Test
    fun doSimpleTest() {

        NxBus.toObservable<String>().subscribe { println("Subscribe result [$it]") }

        NxBus.post("Post Data 1")
        NxBus.post("Post Data 2")

    }

}