package nlab.practice.dagger.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import nlab.practice.PracticeApplication
import nlab.practice.dagger.module.AppModule
import javax.inject.Singleton

/**
 * @author Doohyun
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class])
interface AppComponent {

    fun inject(application: PracticeApplication)
}