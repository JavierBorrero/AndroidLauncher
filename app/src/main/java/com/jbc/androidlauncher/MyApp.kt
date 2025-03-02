package com.jbc.androidlauncher

import android.app.Application
import com.jbc.androidlauncher.di.AppModule
import com.jbc.androidlauncher.di.AppModuleImpl

class MyApp: Application() {

    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }

}