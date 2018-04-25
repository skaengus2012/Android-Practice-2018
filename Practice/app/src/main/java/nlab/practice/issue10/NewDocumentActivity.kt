package nlab.practice.issue10

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_new_document.*
import nlab.practice.R

/**
 * DocumentCentricActivity 가 작업추가할 화면에 대한 정의.
 *
 * @author ndh1002
 */
class NewDocumentActivity : AppCompatActivity() {

    companion object {
        const val COUNT_EXTRA = "COUNT_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_document)

        setTextByCount(intent)
        tvAlreadyCreated.visibility = View.GONE

        btnRemoveDocument.setOnClickListener({ exitDocument() })
    }

    /**
     * 새로운 [intent] 가 들어온다면, 이미 생성되었다는 라벨을 표시한다.
     *
     * @param intent
     */
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        setTextByCount(intent)
        tvAlreadyCreated.visibility = View.VISIBLE
    }

    /**
     * [intent] 로부터 카운트를 추출하여, 텍스트뷰에 세팅한다.
     *
     * @param intent
     */
    private fun setTextByCount(intent: Intent?) {
        val countStr = intent?.getStringExtra(COUNT_EXTRA)
        with(countStr) {
            checkNotNull(this)

            tvCount.text = this!!
        }
    }

    /**
     * 도큐먼트 종료.
     *
     * 화면을 종료하며, 앱 태스크도 같이 삭제.
     * -> 하지만 프로세스도 종료되는 것은 아님.
     *
     */
    private fun exitDocument() {
        // FIXME ndh1002 API 21 이하는 아래 정보를 확인해보자.
        // https://stackoverflow.com/questions/27368525/finishandremovetask-available-on-api-21
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask()
        }
    }
}
