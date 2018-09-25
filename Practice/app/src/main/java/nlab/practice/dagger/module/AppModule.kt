package nlab.practice.dagger.module

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import nlab.practice.dagger.component.DaggerTestComponent
import nlab.practice.issue32.DaggerTestActivity

/**
 * @author Doohyun
 */
@Module(subcomponents = [DaggerTestComponent::class])
abstract class AppModule {

    @Binds
    @IntoMap
    @ActivityKey(DaggerTestActivity::class)
    abstract fun bindDaggerTestActivity(builder: DaggerTestComponent.Builder) : AndroidInjector.Factory<out Activity>
}