package com.krithi.frauddetector

import android.app.Application

class SafeLensApplication : Application() {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        container = AppContainer(this)
    }
}