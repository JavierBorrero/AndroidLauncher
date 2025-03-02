package com.jbc.androidlauncher.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbc.androidlauncher.data.AppInfo
import com.jbc.androidlauncher.data.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppListViewModel (private val appRep: AppRepository): ViewModel() {

    // Listado apps
    private val _apps = MutableStateFlow<List<AppInfo>>(emptyList())
    val apps: StateFlow<List<AppInfo>> = _apps.asStateFlow()

    // App seleccionada de la lista
    private val _selectedApp = MutableStateFlow<AppInfo?>(null)
    val selectedApp: StateFlow<AppInfo?> = _selectedApp.asStateFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            _apps.value = appRep.getAllApps()
        }
    }

    // Al mantener presionado guardamos el valor del app
    fun onAppLongPress(app: AppInfo) {
        _selectedApp.value = app
    }

    // Al cerrar el dialog cambiamos a null la app seleccionada
    fun dismissDialog() {
        _selectedApp.value = null
    }

    fun onAddToMain() {
        appRep.addAppToMainScreen(_selectedApp.value!!)
    }
}