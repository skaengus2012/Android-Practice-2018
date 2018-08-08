package nlab.practice.util.eventbus

import com.hwangjr.rxbus.Bus

/**
 * Bus 중개자 클래스 정의
 *
 * @author Doohyun
 */
object RxBus {

    val Instance : Bus by lazy { Bus() }



}