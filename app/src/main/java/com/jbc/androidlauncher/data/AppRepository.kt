package com.jbc.androidlauncher.data

import kotlinx.coroutines.flow.StateFlow

interface AppRepository {

    fun getAllApps(): List<AppInfo>

    fun addAppToMainScreen(app: AppInfo)

    val mainScreenApps: StateFlow<List<AppInfo>>

}