package me.proton.jobforandroid.gbandroidpro.view.main

import io.reactivex.rxjava3.core.Observable
import me.proton.jobforandroid.gbandroidpro.di.NAME_LOCAL
import me.proton.jobforandroid.gbandroidpro.di.NAME_REMOTE
import me.proton.jobforandroid.gbandroidpro.model.AppState
import me.proton.jobforandroid.gbandroidpro.model.repository.Repository
import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel
import me.proton.jobforandroid.gbandroidpro.viewmodel.Interactor
import javax.inject.Named

class MainInteractor(
    @Named(NAME_REMOTE) val repositoryRemote: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            repositoryRemote
        } else {
            repositoryLocal
        }.getData(word).map { AppState.Success(it) }
    }
}
