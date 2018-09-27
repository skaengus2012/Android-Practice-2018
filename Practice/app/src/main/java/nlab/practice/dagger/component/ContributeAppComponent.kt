package nlab.practice.dagger.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import nlab.practice.PracticeApplication
import nlab.practice.dagger.module.InjectorModule
import javax.inject.Singleton

/**
 * @author Doohyun
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    InjectorModule::class
])
interface ContributeAppComponent : AndroidInjector<PracticeApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<PracticeApplication>()
}