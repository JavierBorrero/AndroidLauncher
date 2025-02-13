package com.jbc.androidlauncher.data

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager

class AppRepository(private val packageManager: PackageManager) {

    fun getAllApps(): List<InfoApp> {
        val packagesAll = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

        val packagesByUser = packagesAll
            .filter { (it.flags and ApplicationInfo.FLAG_SYSTEM) == 0 }
            .mapNotNull { appInfo ->
                val name = packageManager.getApplicationLabel(appInfo).toString()
                val launchIntent = packageManager.getLaunchIntentForPackage(appInfo.packageName)

                if(launchIntent != null) {
                    InfoApp(name, appInfo.packageName, launchIntent)
                } else {
                    null
                }
            }

        return packagesByUser
    }
}