package me.proton.jobforandroid.gbandroidpro.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@TranslatorApp) }
    }

}

