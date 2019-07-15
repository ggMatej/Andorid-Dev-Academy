package hr.ferit.matejmijic.ada_taskie

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

//Nije mi baš jasno
class Taskie: Application() {

    companion object {
        lateinit var instance: Taskie
            private set

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}