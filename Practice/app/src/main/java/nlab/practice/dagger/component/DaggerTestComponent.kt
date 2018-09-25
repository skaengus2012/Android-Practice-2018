package nlab.practice.dagger.component

import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import nlab.practice.dagger.module.DaggerTestModule
import nlab.practice.dagger.scope.ActivityScope
import nlab.practice.issue32.DaggerTestActivity

/**
 * DaggerTestActivity 주입관계 정의
 *
 * @author Doohyun
 */
@ActivityScope
@Subcomponent(modules = [DaggerTestModule::class])
interface DaggerTestComponent : AndroidInjector<DaggerTestActivity>{

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DaggerTestActivity>()
}