package nlab.practice.issue10

import android.content.Intent
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
}
