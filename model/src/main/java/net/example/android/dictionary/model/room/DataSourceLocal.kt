package net.example.android.dictionary.model.room

import net.example.android.dictionary.model.AppState

interface DataSourceLocal<T> : net.example.android.dictionary.model.datasource.DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}
