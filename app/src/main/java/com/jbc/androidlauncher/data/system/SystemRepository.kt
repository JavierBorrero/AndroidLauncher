package com.jbc.androidlauncher.data.system

import kotlinx.coroutines.flow.StateFlow

interface SystemRepository {

    // nivel bateria
    val batteryLevel: StateFlow<Int>

    // obtener nivel de bateria con BatteryManager
    fun getBatteryLevel(): Int

    // actualizar el valor de la variable bateria
    fun updateBatteryLevel()

}