package net.example.android.dictionary.model.repository

interface Repository<T> {

    suspend fun getData(word: String): T
}
