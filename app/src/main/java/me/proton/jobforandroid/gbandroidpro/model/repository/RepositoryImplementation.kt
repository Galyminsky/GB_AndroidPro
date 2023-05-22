package me.proton.jobforandroid.gbandroidpro.model.repository

import me.proton.jobforandroid.gbandroidpro.model.datasource.DataSource
import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}
