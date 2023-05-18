package me.proton.jobforandroid.gbandroidpro.model.repository

interface Repository<T> {
    suspend fun getData(word: String): T
}

