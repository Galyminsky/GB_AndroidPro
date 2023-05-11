package me.proton.jobforandroid.gbandroidpro.presenter.main

import io.reactivex.rxjava3.core.Observable
import me.proton.jobforandroid.gbandroidpro.model.AppState
import me.proton.jobforandroid.gbandroidpro.model.repository.Repository
import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel
import me.proton.jobforandroid.gbandroidpro.presenter.Interactor

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}
