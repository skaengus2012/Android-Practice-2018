package nlab.practice

import android.app.Activity
import android.app.Application
import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import nlab.practice.dagger.component.DaggerAppComponent
import nlab.practice.dagger.component.DaggerContributeAppComponent
import javax.inject.Inject

/**
 * Dagger 기본에 해당하는 Application 단 정의
 *
class PracticeApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    // 어플리케이션 정보를 전역적으로 접근이 가능하겠금 처리.
    companion object {
        private lateinit var Instance : Application

        fun getContext() : Context = Instance
    }

    override fun onCreate() {
        super.onCreate()

        Instance = this

        DaggerAppComponent.create().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}
 */

/**
 * ContributeAndroidInjector 를 사용하는 Dagger 버전의 Application 구성
 */
class PracticeApplication : DaggerApplication() {

    // 어플리케이션 정보를 전역적으로 접근이 가능하겠금 처리.
    companion object {
        private lateinit var Instance : Application

        fun getContext() : Context = Instance
    }

    override fun onCreate() {
        super.onCreate()

        Instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>
        = DaggerContributeAppComponent.builder().create(this)
}
