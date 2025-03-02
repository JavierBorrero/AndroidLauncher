package com.jbc.androidlauncher.presentation.screens.main

import androidx.lifecycle.ViewModel
import com.jbc.androidlauncher.data.AppInfo
import com.jbc.androidlauncher.data.AppRepository
import com.jbc.androidlauncher.data.system.SystemRepository
import kotlinx.coroutines.flow.StateFlow

class MainScreenViewModel(
    appRep: AppRepository,
    private val systemRep: SystemRepository,
): ViewModel() {

    val mainScreenApps: StateFlow<List<AppInfo>> = appRep.mainScreenApps

    // nivel de bateria del repository
    val batteryLevel = systemRep.batteryLevel

    // funcion a llamar para actualizar la bateria
    fun refreshBatteryLevel() {
        systemRep.updateBatteryLevel()
    }
}