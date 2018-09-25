package nlab.practice.dagger.module

import dagger.Binds
import dagger.Module
import nlab.practice.dagger.scope.ActivityScope
import nlab.practice.issue32.DaggerTestActivity
import nlab.practice.issue32.DaggerTestActivityContract
import nlab.practice.issue32.DaggerTestPresenterImpl

/**
 * Dagger Test 프리젠터에 필요한 모듈 정
 *
 * @author Doohyun
 */
@Module
abstract class DaggerTestModule {

    @ActivityScope
    @Binds
    abstract fun provideView(activity: DaggerTestActivity) : DaggerTestActivityContract.View

    @ActivityScope
    @Binds
    abstract fun providePresenter(presenter: DaggerTestPresenterImpl) : DaggerTestActivityContract.Presenter
}