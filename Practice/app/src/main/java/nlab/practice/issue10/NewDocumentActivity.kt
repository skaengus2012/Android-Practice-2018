package nlab.practice.issue10

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import nlab.practice.R

/**
 * DocumentCentricActivity 가 작업추가할 화면에 대한 정의.
 *
 * @author ndh1002
 */
class NewDocumentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_document)
    }
}
