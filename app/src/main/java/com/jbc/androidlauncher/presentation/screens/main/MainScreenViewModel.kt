package com.jbc.androidlauncher.presentation.screens.main

import androidx.lifecycle.ViewModel
import com.jbc.androidlauncher.data.AppInfo
import com.jbc.androidlauncher.data.AppRepository
import com.jbc.androidlauncher.data.system.SystemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel(
    appRep: AppRepository,
    private val systemRep: SystemRepository,
): ViewModel() {

    val mainScreenApps: StateFlow<List<AppInfo>> = appRep.mainScreenApps

    // igual que en el app repository
    // asignar a _batteryLevel el valor de getBatteryLevel
    private val _batteryLevel = MutableStateFlow(systemRep.getBatteryLevel())
    val batteryLevel = _batteryLevel.asStateFlow()

    private val _systemTime = MutableStateFlow(systemRep.getSystemDate())
    val systemTime = _systemTime.asStateFlow()

    val clockIntent = systemRep.getClockIntent()
    val calendarIntent = systemRep.getCalendarIntent()

    // actualizar el valor de _batteryLevel con la funcion de abajo
    fun updateBatteryLevel() {
        _batteryLevel.value = systemRep.getBatteryLevel()
    }

    fun updateFormatTime() {
        _systemTime.value = systemRep.getSystemDate()
    }
}