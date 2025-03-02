package com.jbc.androidlauncher.data

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppRepositoryImpl (private val context: Context): AppRepository {

    private val _mainScreenApps =  MutableStateFlow<List<AppInfo>>(emptyList())
    override val mainScreenApps: StateFlow<List<AppInfo>> get() = _mainScreenApps.asStateFlow()

    override fun getAllApps(): List<AppInfo> {
        val packageManager = context.packageManager

        return packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            .filter { appInfo ->
                packageManager.getLaunchIntentForPackage(appInfo.packageName) != null
            }
            .sortedBy { appInfo ->
                packageManager.getApplicationLabel(appInfo).toString().lowercase()
            }
            .map { appInfo ->
                AppInfo(
                    name = packageManager.getApplicationLabel(appInfo).toString(),
                    icon = packageManager.getApplicationIcon(appInfo),
                    launchIntent = packageManager.getLaunchIntentForPackage(appInfo.packageName)!!
                )
            }
    }

    override fun addAppToMainScreen(app: AppInfo) {
        val currentList = _mainScreenApps.value.toMutableList()
        if (!currentList.contains(app)) {
            currentList.add(app)
            _mainScreenApps.value = currentList
            Log.d("APPREPOSITORY", "${app.name}, ${currentList.size}")
        }else {
            Log.d("APPREPOSITORY", "ya en la lista")
        }
    }
}