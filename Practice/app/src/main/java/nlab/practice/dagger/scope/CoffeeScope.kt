package nlab.practice.dagger.scope

import javax.inject.Scope

/**
 * SubComponent 지정을 위한 CustomScope 정의
 *
 * @author Doohyun
 */
@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class CoffeeScope