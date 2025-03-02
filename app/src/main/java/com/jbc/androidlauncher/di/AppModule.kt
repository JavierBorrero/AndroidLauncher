package com.jbc.androidlauncher.di

import android.content.Context
import com.jbc.androidlauncher.data.AppRepository
import com.jbc.androidlauncher.data.AppRepositoryImpl

interface AppModule {
    val appRepository: AppRepository
}

class AppModuleImpl(
    private val appContext: Context
): AppModule {

    override val appRepository: AppRepository by lazy {
        AppRepositoryImpl(appContext)
    }

}