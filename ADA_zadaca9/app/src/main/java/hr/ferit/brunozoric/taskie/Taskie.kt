package hr.ferit.brunozoric.taskie

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import hr.ferit.brunozoric.taskie.di.domainModule
import hr.ferit.brunozoric.taskie.di.networkingModule
import hr.ferit.brunozoric.taskie.di.presenterModule
import hr.ferit.brunozoric.taskie.di.repositoryModule
import hr.ferit.brunozoric.taskie.prefs.PREFERENCES_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class Taskie: Application() {

    companion object {
        lateinit var instance: Taskie
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        startKoin {
            modules(listOf(networkingModule, presenterModule, domainModule, repositoryModule))
            androidContext(this@Taskie)
        }
    }



    fun providePreferences(): SharedPreferences = instance.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
}