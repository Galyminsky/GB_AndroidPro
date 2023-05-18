package me.proton.jobforandroid.gbandroidpro.model.datasource

import io.reactivex.rxjava3.core.Observable
import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented")
    }
}
