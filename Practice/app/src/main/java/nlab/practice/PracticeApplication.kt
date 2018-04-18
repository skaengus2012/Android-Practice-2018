package nlab.practice

import android.app.Application
import android.content.Context

/**
 * 해당 클래스에서 사용하는 어플리케이션 정의.
 *
 * @author ndh1002
 */
class PracticeApplication : Application() {

    companion object {
        private lateinit var Instance : Application

        fun getContext() : Context = Instance
    }

    override fun onCreate() {
        super.onCreate()

        Instance = this
    }

}