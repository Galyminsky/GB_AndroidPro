package me.proton.jobforandroid.gbandroidpro.application

import android.app.Application
import me.proton.jobforandroid.gbandroidpro.di.appModule
import me.proton.jobforandroid.gbandroidpro.di.interactorModule
import me.proton.jobforandroid.gbandroidpro.di.repositoryModule
import me.proton.jobforandroid.gbandroidpro.di.viewModelModule
import org.koin.core.context.startKoin

class TranslatorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(appModule, interactorModule, repositoryModule, viewModelModule))
        }
    }
}

