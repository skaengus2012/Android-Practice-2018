package nlab.practice.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dev_tutorial.*
import nlab.practice.R


/**
 * 개발자 튜토리얼 화면 정의
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/8
 *
 * @author ndh1002
 */
class DevTutorialActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MESSAGE = "nlab.practice.ui.MESSAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dev_tutorial)

        button.setOnClickListener({sendMessage()})
    }

    /**
     * DisplayMessage 로 메세지를 보내기 위한 행위 정의
     */
    private fun sendMessage() =
            Intent(this, DisplayMessageActivity::class.java)
                    .apply {
                        val message = editText.text?.toString() ?: ""
                        putExtra(EXTRA_MESSAGE, message)
                    }
                    .let { startActivity(it) }

}
