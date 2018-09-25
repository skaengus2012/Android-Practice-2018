package nlab.practice.dagger.module

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import nlab.practice.dagger.component.DaggerTestFragmentComponent
import nlab.practice.dagger.scope.ActivityScope
import nlab.practice.issue32.DaggerTestActivity
import nlab.practice.issue32.DaggerTestActivityContract
import nlab.practice.issue32.DaggerTestFragment
import nlab.practice.issue32.DaggerTestPresenterImpl

/**
 * Dagger Test 프리젠터에 필요한 모듈 정
 *
 * @author Doohyun
 */
@Module(subcomponents = [DaggerTestFragmentComponent::class])
abstract class DaggerTestModule {

    @ActivityScope
    @Binds
    abstract fun provideView(activity: DaggerTestActivity) : DaggerTestActivityContract.View

    @ActivityScope
    @Binds
    abstract fun providePresenter(presenter: DaggerTestPresenterImpl) : DaggerTestActivityContract.Presenter

    @Binds
    @IntoMap
    @FragmentKey(DaggerTestFragment::class)
    abstract fun bindMoviesFragment(builder: DaggerTestFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}