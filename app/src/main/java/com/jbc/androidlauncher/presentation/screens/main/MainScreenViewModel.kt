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

    private val _batteryLevel = MutableStateFlow(systemRep.getBatteryLevel())
    val batteryLevel = _batteryLevel.asStateFlow()

    private val _systemTime: MutableStateFlow<List<String>> = MutableStateFlow(systemRep.getSystemDate())
    val systemTime: StateFlow<List<String>> = _systemTime.asStateFlow()

    val clockIntent = systemRep.getClockIntent()
    val calendarIntent = systemRep.getCalendarIntent()

    fun updateBatteryLevel() {
        _batteryLevel.value = systemRep.getBatteryLevel()
    }

    fun updateFormatTime() {
        _systemTime.value = systemRep.getSystemDate()
    }
}