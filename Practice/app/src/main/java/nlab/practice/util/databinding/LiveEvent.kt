package nlab.practice.util.databinding

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import android.util.Log
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Life Cycle 을 타는 이벤트 타입 정의
 *
 * @author Doohyun
 */
class LiveEvent<T> : MutableLiveData<T>() {

    companion object {
        const val TAG = "LiveEvent"
    }

    private val _pending : AtomicBoolean = AtomicBoolean(false)

    @MainThread
    override fun setValue(value: T?) {
        _pending.set(true)
        super.setValue(value)
    }

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {

        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }

        super.observe(owner, Observer {
            if (_pending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        })
    }

    fun call() {
        value = null
    }

}