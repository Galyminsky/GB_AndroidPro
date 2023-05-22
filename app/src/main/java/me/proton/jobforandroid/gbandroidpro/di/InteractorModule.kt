package me.proton.jobforandroid.gbandroidpro.di

import me.proton.jobforandroid.gbandroidpro.view.history.HistoryInteractor
import me.proton.jobforandroid.gbandroidpro.view.main.MainInteractor
import org.koin.core.qualifier.named
import org.koin.dsl.module

val interactorModule = module {
    factory { HistoryInteractor(get(named("Remote")), get(named("Local"))) }
    factory { MainInteractor(get(named("Remote")), get(named("Local"))) }
}