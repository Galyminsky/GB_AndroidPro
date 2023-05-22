package me.proton.jobforandroid.gbandroidpro.model.repository

import me.proton.jobforandroid.gbandroidpro.model.AppState
import me.proton.jobforandroid.gbandroidpro.model.datasource.RepositoryLocal
import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel
import me.proton.jobforandroid.gbandroidpro.model.room.DataSourceLocal

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}