package me.proton.jobforandroid.gbandroidpro.di

import dagger.Module
import dagger.Provides
import me.proton.jobforandroid.gbandroidpro.model.datasource.DataSource
import me.proton.jobforandroid.gbandroidpro.model.datasource.RoomDataBaseImplementation
import me.proton.jobforandroid.gbandroidpro.model.repository.Repository
import me.proton.jobforandroid.gbandroidpro.model.repository.RepositoryImplementation
import me.proton.jobforandroid.gbandroidpro.model.repository.RetrofitImplementation
import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<DataModel>> =
        RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<DataModel>> =
        RoomDataBaseImplementation()
}
