package me.proton.jobforandroid.gbandroidpro.di

import androidx.room.Room
import me.proton.jobforandroid.gbandroidpro.model.datasource.BaseInterceptor
import me.proton.jobforandroid.gbandroidpro.model.repository.ApiService
import me.proton.jobforandroid.gbandroidpro.model.repository.RetrofitImplementation
import me.proton.jobforandroid.gbandroidpro.model.room.HistoryDataBase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { provideRetrofit() }
    single { provideService() }
    single { provideOkHttpClient() }
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
}

private fun provideService(): ApiService {
    return provideRetrofit().create(ApiService::class.java)
}

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(RetrofitImplementation.BASE_URL_LOCATIONS)
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOkHttpClient())
        .build()
}

private fun provideOkHttpClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor(BaseInterceptor.interceptor)
    httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    return httpClient.build()
}