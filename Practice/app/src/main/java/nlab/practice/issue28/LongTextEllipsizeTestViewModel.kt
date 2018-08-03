package nlab.practice.issue28

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import nlab.practice.util.databinding.LiveEvent

/**
 * Long Text Ellipsize 테스트에 대한 뷰모델 정의
 *
 * @author Doohyun
 * @since 2018. 08. 03
 */
class LongTextEllipsizeTestViewModel(application: Application)
    : AndroidViewModel(application), TextWatcher {

    companion object {
        private const val TAG : String = "LongTextEllipsizeTest"
    }

    val naveViewInvalidateEvent : LiveEvent<Nothing> = LiveEvent()
    val name : ObservableField<String> = ObservableField()

    // Nothing...
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    // Nothing...
    override fun afterTextChanged(p0: Editable?){}

    override fun onTextChanged(changedValue: CharSequence?, p1: Int, p2: Int, p3: Int) {
        Log.d(TAG, "Invoke onTextChanged.")

        changedValue?.let {
            name.set(it.toString())
            naveViewInvalidateEvent.call()
        }
    }
}