package me.proton.jobforandroid.gbandroidpro.di

import me.proton.jobforandroid.gbandroidpro.view.main.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { MainViewModel(get()) }
}