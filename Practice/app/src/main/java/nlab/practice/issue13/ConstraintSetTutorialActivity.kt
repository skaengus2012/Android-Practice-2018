package nlab.practice.issue13

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.constraint.ConstraintSet
import com.transitionseverywhere.TransitionManager
import kotlinx.android.synthetic.main.activity_constraint_set_tutorial.*
import nlab.practice.R

/**
 * ConstraintSet 을 이용한 애니메이션 처리
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/13
 *
 * @author ndh1002
 */
class ConstraintSetTutorialActivity : AppCompatActivity() {

    private var constraintSet1 : ConstraintSet? = null
    private var constraintSet2 : ConstraintSet? = null
    private var constraintSet3 : ConstraintSet? = null

    private var changeState = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_set_tutorial)

        initConstraintSet()

        btnAnimation.setOnClickListener({ animate() })
    }

    /**
     * ConstraintSet 초기화.
     */
    private fun initConstraintSet() {
        constraintSet1 = ConstraintSet().apply { clone(layout) }
        constraintSet2 = createConstraintSet(R.layout.activity_constraint_set_tutorial_alt)
        constraintSet3 = createConstraintSet(R.layout.activity_constraint_set_tutorial_alt_2)
    }

    /**
     * [animationLayoutRes] 를 이용하여 ConstraintSet 을 제작.
     *
     * @param animationLayoutRes
     * @return
     */
    private fun createConstraintSet(@LayoutRes animationLayoutRes: Int) : ConstraintSet =
            ConstraintSet().apply {
                clone(this@ConstraintSetTutorialActivity, animationLayoutRes)
            }

    /**
     * animation 작업 수행.
     */
    private fun animate() {
        TransitionManager.beginDelayedTransition(layout)

        when(changeState) {
            0 -> {
                constraintSet2?.applyTo(layout)
                changeState = 1
            }

            1 -> {
                constraintSet3?.applyTo(layout)
                changeState = 2
            }

            else -> {
                constraintSet1?.applyTo(layout)
                changeState = 0
            }
        }
    }

}
