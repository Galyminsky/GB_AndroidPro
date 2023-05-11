package me.proton.jobforandroid.gbandroidpro.viewmodel

import io.reactivex.rxjava3.core.Observable

interface Interactor<T : Any> {
    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}