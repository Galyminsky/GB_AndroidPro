package net.example.android.dictionary.model.datasource

import net.example.android.dictionary.model.AppState
import net.example.android.dictionary.model.repository.Repository


interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}
