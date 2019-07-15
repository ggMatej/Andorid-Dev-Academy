package osc.androiddevacademy.movieapp

import android.app.Application
import android.content.Context

class App: Application(){

    companion object {
        private lateinit var instance: App

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

}