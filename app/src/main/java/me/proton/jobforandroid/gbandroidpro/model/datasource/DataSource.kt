package me.proton.jobforandroid.gbandroidpro.model.datasource

interface DataSource<T> {
    suspend fun getData(word: String): T
}
