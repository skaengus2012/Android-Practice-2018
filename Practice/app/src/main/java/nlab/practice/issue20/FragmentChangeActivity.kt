package nlab.practice.issue20

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_fragment_change.*
import nlab.practice.R

/**
 * 프래그먼트 교체 연습 화면 정의
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/20
 *
 * @author ndh1002
 */
class FragmentChangeActivity : AppCompatActivity() {

    private var fragmentCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_change)

        // 스택 추가를 하지 않는 프래그먼트 변경 처리.
        btnChangeFragment.setOnClickListener({ replaceFragmentByNoneStack() })

        // 스택 추가를 하는 프래그먼트 변경 처리.
        btnChangeFragmentWithStack.setOnClickListener({ replaceFragmentByStack() })

        // 초기화 처리.
        // 처음에는 스택쌓지않는 프래그먼트 생산.
        supportFragmentManager
                .beginTransaction()
                .add(R.id.layoutFragmentContainer, createNoneStackFragment())
                .commit()
    }

    /**
     * 스택을 쌓지않는 프래그먼트 대체.
     *
     * @return
     */
    private fun replaceFragmentByNoneStack() {
        val fragment = createNoneStackFragment()

        createReplaceTransaction(fragment).commit()
    }

    /**
     * 스택을 쌓는 프래그먼트 대체.
     *
     * @return
     */
    private fun replaceFragmentByStack() {
        val fragment = CountFragment.Create(fragmentCount++, R.color.colorPrimary)

        createReplaceTransaction(fragment).addToBackStack(null).commit()
    }

    /**
     * 스택을 쌓지 않는 프래그먼트 생산.
     */
    private fun createNoneStackFragment() : CountFragment = CountFragment.Create(fragmentCount++, R.color.colorAccent)


    /**
     * [fragment] 를 추가하는 트랜잭션 생성.
     *
     * @param fragment
     * @return
     */
    private fun createReplaceTransaction(fragment: CountFragment) : FragmentTransaction =
        supportFragmentManager.beginTransaction().replace(R.id.layoutFragmentContainer, fragment)

}
