package nlab.practice

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 앱 테스트를 위한 인트로 화면 정의
 *
 * @author ndh1002
 */
class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvLabels.adapter = MainItemListAdapter()

        performAppScheme(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        performAppScheme(intent)
    }

    /**
     * [intent] 내부의 스킴을 이용한 행위를 수행한다.
     *
     * @param intent
     */
    fun performAppScheme(intent: Intent?) = intent?.data?.let {
        doAppSchemeBehavior(this, it)
    }

}
