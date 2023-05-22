package me.proton.jobforandroid.gbandroidpro.model.datasource

import me.proton.jobforandroid.gbandroidpro.model.AppState
import me.proton.jobforandroid.gbandroidpro.model.repository.Repository

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
}