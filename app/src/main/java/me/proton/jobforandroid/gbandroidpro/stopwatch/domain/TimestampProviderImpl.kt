package me.proton.jobforandroid.gbandroidpro.stopwatch.domain

class TimestampProviderImpl : ITimestampProvider {
    override fun getMilliseconds(): Long =
        System.currentTimeMillis()
}