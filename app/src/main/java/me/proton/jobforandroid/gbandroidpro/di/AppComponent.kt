package me.proton.jobforandroid.gbandroidpro.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import me.proton.jobforandroid.gbandroidpro.view.main.MainActivity
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class]
)

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContent(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)
}
