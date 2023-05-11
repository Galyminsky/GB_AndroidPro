package me.proton.jobforandroid.gbandroidpro.model.datasource

import io.reactivex.rxjava3.core.Observable
import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}
