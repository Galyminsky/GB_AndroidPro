package me.proton.jobforandroid.gbandroidpro.viewmodel

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}