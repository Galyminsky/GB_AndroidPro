package me.proton.jobforandroid.gbandroidpro.application

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import me.proton.jobforandroid.gbandroidpro.di.AppComponent



@Component.Factory
interface Factory {
    fun create(@BindsInstance app: TranslatorApp): AppComponent
}

class TranslatorApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .factory()
            .create(this)
    }
}

