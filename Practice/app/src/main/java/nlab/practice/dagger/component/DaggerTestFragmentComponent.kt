package nlab.practice.dagger.component

import dagger.Subcomponent
import dagger.android.AndroidInjector
import nlab.practice.dagger.module.DaggerTestFragmentModule
import nlab.practice.dagger.scope.FragmentScope
import nlab.practice.issue32.DaggerTestFragment

/**
 * @author Doohyun
 */
@FragmentScope
@Subcomponent(modules = [(DaggerTestFragmentModule::class)])
interface DaggerTestFragmentComponent : AndroidInjector<DaggerTestFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DaggerTestFragment>()
}