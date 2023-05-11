package me.proton.jobforandroid.gbandroidpro.di

import dagger.Module
import dagger.Provides
import me.proton.jobforandroid.gbandroidpro.model.repository.Repository
import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel
import me.proton.jobforandroid.gbandroidpro.view.main.MainInteractor
import javax.inject.Named

@Module

class InteractorModule {

    @Provides
    fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ): MainInteractor = MainInteractor(repositoryRemote, repositoryLocal)
}
