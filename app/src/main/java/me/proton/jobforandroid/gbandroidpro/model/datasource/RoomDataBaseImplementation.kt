package me.proton.jobforandroid.gbandroidpro.model.datasource

import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented")
    }
}
