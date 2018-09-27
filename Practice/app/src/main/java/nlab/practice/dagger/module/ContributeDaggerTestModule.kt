package nlab.practice.dagger.module

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import nlab.practice.dagger.scope.ActivityScope
import nlab.practice.dagger.scope.FragmentScope
import nlab.practice.issue32.*

/**
 * Dagger Test 프리젠터에 필요한 모듈 정
 *
 * @author Doohyun
 */
@Module
abstract class ContributeDaggerTestModule {

    @ActivityScope
    @Binds
    abstract fun provideView(activity: DaggerTestContributeActivity) : DaggerTestActivityContract.View

    @ActivityScope
    @Binds
    abstract fun providePresenter(presenter: DaggerTestPresenterImpl) : DaggerTestActivityContract.Presenter

    @FragmentScope
    @ContributesAndroidInjector(modules = [DaggerTestFragmentModule::class])
    abstract fun daggerTestFragment() : DaggerTestFragment
}