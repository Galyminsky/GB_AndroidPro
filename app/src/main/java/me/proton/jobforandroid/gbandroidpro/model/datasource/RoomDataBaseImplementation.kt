package me.proton.jobforandroid.gbandroidpro.model.datasource

import me.proton.jobforandroid.gbandroidpro.model.AppState
import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel
import me.proton.jobforandroid.gbandroidpro.model.room.DataSourceLocal
import me.proton.jobforandroid.gbandroidpro.model.room.HistoryDao
import me.proton.jobforandroid.gbandroidpro.utils.network.convertDataModelSuccessToEntity
import me.proton.jobforandroid.gbandroidpro.utils.network.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}