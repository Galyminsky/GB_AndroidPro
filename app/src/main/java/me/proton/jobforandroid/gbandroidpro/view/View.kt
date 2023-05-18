package me.proton.jobforandroid.gbandroidpro.view

import me.proton.jobforandroid.gbandroidpro.model.AppState

interface View {

    fun renderData(appState: AppState)
}
