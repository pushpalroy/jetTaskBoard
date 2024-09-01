package com.jetapps.jettaskboard

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class JetTaskBoardApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Todo: Plant the Timber
//        if (BuildConfig.DEBUG) {
//            Timber.plant(DebugTree())
//        }
    }
}
