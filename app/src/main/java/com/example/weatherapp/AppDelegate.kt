package com.example.weatherapp

import android.app.Application
import com.facebook.stetho.Stetho

class AppDelegate: Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}