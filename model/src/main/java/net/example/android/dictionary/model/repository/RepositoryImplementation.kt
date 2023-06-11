package net.example.android.dictionary.model.repository

import net.example.android.dictionary.model.datasource.DataSource
import net.example.android.dictionary.model.repository.entity.DataModel

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}
