package me.proton.jobforandroid.gbandroidpro.application

import android.app.Application
import me.proton.jobforandroid.gbandroidpro.di.AppComponent
import me.proton.jobforandroid.gbandroidpro.di.DaggerAppComponent


class TranslatorApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appContent(this)
            .build()
    }
}

