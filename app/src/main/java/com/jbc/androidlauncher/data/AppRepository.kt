package com.jbc.androidlauncher.data

import kotlinx.coroutines.flow.StateFlow

interface AppRepository {

    suspend fun getAllApps(): List<AppInfo>

    fun addAppToMainScreen(app: AppInfo)

    fun searchQuery(query: String): Boolean

    val mainScreenApps: StateFlow<List<AppInfo>>

}