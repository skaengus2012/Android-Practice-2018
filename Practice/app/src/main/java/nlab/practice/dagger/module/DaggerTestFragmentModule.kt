package nlab.practice.dagger.module

import dagger.Binds
import dagger.Module
import nlab.practice.dagger.scope.FragmentScope
import nlab.practice.issue32.DaggerTestFragment
import nlab.practice.issue32.DaggerTestFragmentContract
import nlab.practice.issue32.DaggerTestFragmentPresenterImpl

/**
 * @author Doohyun
 */
@Module
abstract class DaggerTestFragmentModule {

    @Binds
    @FragmentScope
    abstract fun provideView(fragment: DaggerTestFragment) : DaggerTestFragmentContract.View

    @Binds
    @FragmentScope
    abstract fun providePresenter(fragment: DaggerTestFragmentPresenterImpl) : DaggerTestFragmentContract.Presenter
}