package me.proton.jobforandroid.gbandroidpro.model.repository.entity

import com.google.gson.annotations.Expose

class DataModel(
    @Expose val text: String?,
    @Expose val meanings: List<Meanings>?
)
