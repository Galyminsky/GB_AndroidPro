package net.example.android.dictionary.model.repository

import kotlinx.coroutines.Deferred
import net.example.android.dictionary.model.repository.entity.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<DataModel>>

}
