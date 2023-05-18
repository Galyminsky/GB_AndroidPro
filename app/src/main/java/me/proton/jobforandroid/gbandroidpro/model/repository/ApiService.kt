package me.proton.jobforandroid.gbandroidpro.model.repository

import io.reactivex.rxjava3.core.Observable
import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>
}
