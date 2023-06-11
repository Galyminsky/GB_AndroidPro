package me.proton.jobforandroid.gbandroidpro.di

import androidx.room.Room
import net.example.android.dictionary.model.datasource.RepositoryLocal
import net.example.android.dictionary.model.datasource.RoomDataBaseImplementation
import net.example.android.dictionary.model.repository.Repository
import net.example.android.dictionary.model.repository.RepositoryImplementation
import net.example.android.dictionary.model.repository.RepositoryImplementationLocal
import net.example.android.dictionary.model.repository.RetrofitImplementation
import net.example.android.dictionary.model.repository.entity.DataModel
import net.example.android.dictionary.model.room.HistoryDataBase
import me.proton.jobforandroid.gbandroidpro.view.main.MainActivity
import me.proton.jobforandroid.gbandroidpro.view.main.MainInteractor
import me.proton.jobforandroid.gbandroidpro.view.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.lang.reflect.Array.get

fun injectDependencies() = loadModules

private val loadModules by lazy {
    loadKoinModules(listOf(application, mainScreen))
}

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }

    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(
            RoomDataBaseImplementation(get())
        )
    }
}

val mainScreen = module {
    scope(named<MainActivity>()) {
        scoped { MainInteractor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }
}
