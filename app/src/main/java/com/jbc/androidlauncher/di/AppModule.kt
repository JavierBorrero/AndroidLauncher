package com.jbc.androidlauncher.di

import android.content.Context
import com.jbc.androidlauncher.data.AppRepository
import com.jbc.androidlauncher.data.AppRepositoryImpl
import com.jbc.androidlauncher.data.system.SystemRepository
import com.jbc.androidlauncher.data.system.SystemRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface AppModule {
    val appRepository: AppRepository
    val systemRepository: SystemRepository
}

class AppModuleImpl(
    private val appContext: Context
): AppModule {

    override val appRepository: AppRepository by lazy {
        val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default

        AppRepositoryImpl(appContext, defaultDispatcher)
    }

    override val systemRepository: SystemRepository by lazy {
        SystemRepositoryImpl(appContext)
    }

}