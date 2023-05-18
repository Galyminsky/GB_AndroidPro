package me.proton.jobforandroid.gbandroidpro.model

import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel

sealed class AppState {

    data class Success(val data: List<DataModel>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}
