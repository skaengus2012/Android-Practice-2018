package nlab.practice.dagger.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nlab.practice.dagger.scope.ActivityScope
import nlab.practice.issue32.DaggerTestContributeActivity

/**
 * ContributeAndroidInjector 를 사용하는 모듈 정의
 *
 * @author Doohyun
 */
@Module
abstract class InjectorModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ContributeDaggerTestModule::class])
    abstract fun daggerTestContributeActivity() : DaggerTestContributeActivity
}