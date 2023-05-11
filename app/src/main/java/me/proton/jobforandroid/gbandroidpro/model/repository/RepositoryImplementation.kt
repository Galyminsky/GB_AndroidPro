package me.proton.jobforandroid.gbandroidpro.model.repository

import io.reactivex.rxjava3.core.Observable
import me.proton.jobforandroid.gbandroidpro.model.datasource.DataSource
import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}
