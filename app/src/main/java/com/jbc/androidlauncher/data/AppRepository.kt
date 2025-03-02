package com.jbc.androidlauncher.data

interface AppRepository {

    fun getAllApps(): List<AppInfo>

    fun addAppToMainScreen(app: AppInfo)

}