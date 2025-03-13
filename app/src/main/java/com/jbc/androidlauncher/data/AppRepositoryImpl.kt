package com.jbc.androidlauncher.data

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class AppRepositoryImpl (
    private val context: Context,
    private val defaultDispatcher: CoroutineDispatcher
): AppRepository {

    private val _mainScreenApps =  MutableStateFlow<List<AppInfo>>(emptyList())
    override val mainScreenApps: StateFlow<List<AppInfo>> get() = _mainScreenApps.asStateFlow()

    override suspend fun getAllApps(): List<AppInfo> = withContext(defaultDispatcher) {
        val packageManager = context.packageManager

        return@withContext packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
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

    override fun isAppOnMainScreen(app: AppInfo): Boolean {
        val currentList = _mainScreenApps.value.toMutableList()

        return currentList.any { it.name == app.name }
    }

    override fun addAppToMainScreen(app: AppInfo) {
        val currentList = _mainScreenApps.value.toMutableList()
        // any{} returns true if at least one element matches the given predicate
        // si NO hay algun elemento en la lista con el name igual al app.name se añade a la lista
        if (!currentList.any { it.name == app.name }) {
            currentList.add(app)
            _mainScreenApps.value = currentList
            Toast.makeText(context, "${app.name} añadida a la lista", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "${app.name} ya esta en la lista", Toast.LENGTH_SHORT).show()
        }
    }

    override fun removeAppFromMainScreen(app: AppInfo) {
        val currentList = _mainScreenApps.value.toMutableList()

        if(currentList.removeIf { currentList.any { it.name == app.name } }) {
            _mainScreenApps.value = currentList
            Toast.makeText(context, "${app.name} eliminada de la lista", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "${app.name} no esta en la lista", Toast.LENGTH_SHORT).show()
        }
    }

    override fun searchQuery(query: String): Boolean {

        return query.contains(query, ignoreCase = true)
    }
}