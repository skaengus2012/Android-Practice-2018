package nlab.practice.dagger.scope

import javax.inject.Scope

/**
 * Activity 생명주기를 따라가는 Scope 레벨 정의
 *
 * @author Doohyun
 */
@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope