package nlab.practice.issue10

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_document_centric.*
import nlab.practice.R

/**
 * 개요화면 예제 정의
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/10
 *
 * @author ndh1002
 */
class DocumentCentricActivity : AppCompatActivity() {

    private var documentCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document_centric)

        // 새 도큐먼트 이벤트 바인딩.
        btnNewDocument.setOnClickListener({ startActivity(createNewDocumentIntent()) })

        // 새 도큐먼트에 멀티 태스크가 추가된 형태로 처리.
        btnNewDocumentWithMultiTask.setOnClickListener({
            createNewDocumentIntent()
                    .addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                    .let { startActivity(it) }
        })
    }

    /**
     * FLAG_ACTIVITY_NEW_DOCUMENT 플래그를 가지며, NewDocumentActivity 를 실행시킬 수 있는 명시적 인텐트를 생산한다.
     *
     * @return
     */
    private fun createNewDocumentIntent(): Intent =
            Intent(this, NewDocumentActivity::class.java).apply {
                this.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
                this.putExtra(NewDocumentActivity.COUNT_EXTRA, (++documentCount).toString())
            }
}
