package com.jbc.androidlauncher.data

import android.content.Context
import android.content.pm.PackageManager

class AppRepository (private val context: Context) {

    fun getAllApps(): List<AppInfo> {
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
}