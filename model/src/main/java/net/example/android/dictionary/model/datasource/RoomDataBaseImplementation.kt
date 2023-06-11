package net.example.android.dictionary.model.datasource

import net.example.android.dictionary.model.AppState
import net.example.android.dictionary.model.repository.entity.DataModel
import net.example.android.dictionary.model.room.DataSourceLocal
import net.example.android.dictionary.model.room.HistoryDao
import net.example.android.dictionary.model.convertDataModelSuccessToEntity
import net.example.android.dictionary.model.mapHistoryEntityToSearchResult

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