package nlab.practice.util.aspect

import android.app.Activity
import android.util.Log
import nlab.practice.util.resource.AppBarConfigManager
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

/**
 * 액티비티 타이틀 변경을 위한 Aspect 정의
 *
 * @author ndh1002
 */
@Aspect
class AppBarTitleAspect {

    companion object {
        const val TAG = "AspectJ"
    }

    @Pointcut("execution(* android.app.Activity.onCreate(..))")
    fun onCreatePointCut(){}

    @After("onCreatePointCut()")
    fun atferOnCreate(joinPoint : JoinPoint) {

        (joinPoint.target as? Activity)
                ?.takeIf { AppBarConfigManager.isHaveLabel(it::class) }
                ?.let {
                    Log.i(TAG, "run aspect -> ${joinPoint.target.javaClass.simpleName}")
                    it.title = AppBarConfigManager.getLabel(it::class)!!
                }
    }
}