package me.proton.jobforandroid.gbandroidpro.model.room

import me.proton.jobforandroid.gbandroidpro.model.AppState
import me.proton.jobforandroid.gbandroidpro.model.datasource.DataSource

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
}