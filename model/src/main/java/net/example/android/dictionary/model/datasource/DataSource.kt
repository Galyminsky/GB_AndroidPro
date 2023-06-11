package net.example.android.dictionary.model.datasource

interface DataSource<T> {

    suspend fun getData(word: String): T
}
