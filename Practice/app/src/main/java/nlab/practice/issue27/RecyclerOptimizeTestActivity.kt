package nlab.practice.issue27

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_recycler_optimize_test.*
import nlab.practice.R

/**
 * RecyclerView 최적화 테스트를 위한 화면 정의
 *
 * RecyclerView 의 아이템에 RecyclerView 를 넣고 최적화를 테스트해볼 생각
 *
 * @see { https://github.com/skaengus2012/Android-Practice-2018/issues/27 }
 *
 * @author Doohyun
 */
class RecyclerOptimizeTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_optimize_test)

        recyclerView.adapter = AdapterViewItemAdapter()
    }


}
