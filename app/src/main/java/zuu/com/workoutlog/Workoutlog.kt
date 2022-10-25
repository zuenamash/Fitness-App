package zuu.com.workoutlog

import android.app.Application
import android.content.Context


class Workoutlog :Application() {
    companion object {
        lateinit var appContext: Context
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}

