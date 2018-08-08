package nlab.practice.issue29

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Produce
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.hwangjr.rxbus.thread.EventThread
import kotlinx.android.synthetic.main.activity_event_bus_tutorial.*
import nlab.practice.R
import nlab.practice.util.resource.showToast

/**
 * Event Bus 튜토리얼 화면 정의
 *
 * @see { https://github.com/skaengus2012/Android-Practice-2018/issues/29 }
 *
 * @author Doohyun
 */
class EventBusTutorialActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "Event Bus Test"
    }

    private val _subscribes : MutableList<OtherSubscriber> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus_tutorial)

        btnSimpleProduce.setOnClickListener {
            // 새로운 구독자 등록
            OtherSubscriber().run {
                _subscribes.add(this)
                RxBus.get().register(this)
            }
        }

        // 버스 등록
        RxBus.get().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        // 버스 해제
        RxBus.get().unregister(this)

        _subscribes.forEach { RxBus.get().unregister(it) }
    }

    /**
     * 구독자에 해당하는 어노테이션 정의
     *
     * 해당 데이터를 받아 처리하는 부분
     */
    @Subscribe
    fun subscribeSimpleData(data : String) = showToast(data)

    /**
     * 공급자에 해당하는 어노테이션 정의
     *
     * 해당 데이터를 공급 (태그 없이 수행)
     */
    @Produce
    fun produceSimpleData() : String = "Produce simple data!"

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = [ Tag(value = "OtherThread") ]
    )
    fun subscribeOtherThread(data : Int) = showToast("output print data $data")

    @Produce(
            thread = EventThread.IO,
            tags = [ Tag(value = "OtherThread") ]
    )
    fun produceSimpleDataInOtherThread() : Int {
        Thread.currentThread().toString().let { Log.i(TAG,"Thread name : $it") }
        Thread.sleep(5000)

        return 0
    }

    class OtherSubscriber {
        @Subscribe(
                thread = EventThread.MAIN_THREAD,
                tags = [ Tag(value = "OtherThread") ]
        )
        fun subscribeOtherThread(data : Int) = showToast("another subscribe output print data $data")
    }

}
