package me.proton.jobforandroid.gbandroidpro.model.repository

import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    suspend fun search(@Query("search") wordToSearch: String): List<DataModel>
}
