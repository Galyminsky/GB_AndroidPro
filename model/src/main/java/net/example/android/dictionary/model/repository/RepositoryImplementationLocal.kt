package net.example.android.dictionary.model.repository

import net.example.android.dictionary.model.AppState
import net.example.android.dictionary.model.datasource.RepositoryLocal
import net.example.android.dictionary.model.repository.entity.DataModel
import net.example.android.dictionary.model.room.DataSourceLocal


class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}
