package me.proton.jobforandroid.gbandroidpro.di

import me.proton.jobforandroid.gbandroidpro.model.datasource.RepositoryLocal
import me.proton.jobforandroid.gbandroidpro.model.datasource.RoomDataBaseImplementation
import me.proton.jobforandroid.gbandroidpro.model.repository.Repository
import me.proton.jobforandroid.gbandroidpro.model.repository.RepositoryImplementation
import me.proton.jobforandroid.gbandroidpro.model.repository.RepositoryImplementationLocal
import me.proton.jobforandroid.gbandroidpro.model.repository.RetrofitImplementation
import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository<List<DataModel>>>(named("Remote")) {
        RepositoryImplementation(
            RetrofitImplementation(get())
        )
    }
    single<RepositoryLocal<List<DataModel>>>(named("Local")) {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}