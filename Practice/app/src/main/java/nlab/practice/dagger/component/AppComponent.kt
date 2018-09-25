package nlab.practice.dagger.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import nlab.practice.PracticeApplication
import nlab.practice.dagger.module.AppModule
import javax.inject.Singleton

/**
 * @author Doohyun
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class,
    AppModule::class
])
interface AppComponent {

    fun inject(application: PracticeApplication)
}