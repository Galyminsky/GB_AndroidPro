package me.proton.jobforandroid.gbandroidpro.view.main

import net.example.android.dictionary.model.AppState
import net.example.android.dictionary.model.datasource.RepositoryLocal
import net.example.android.dictionary.model.repository.Repository
import net.example.android.dictionary.model.repository.entity.DataModel
import me.proton.jobforandroid.gbandroidpro.viewmodel.Interactor


class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}
