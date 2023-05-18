package me.proton.jobforandroid.gbandroidpro.model.datasource

import io.reactivex.rxjava3.core.Observable
import me.proton.jobforandroid.gbandroidpro.model.repository.RetrofitImplementation
import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}
