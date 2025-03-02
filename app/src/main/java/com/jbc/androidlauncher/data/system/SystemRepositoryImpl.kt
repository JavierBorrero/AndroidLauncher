package com.jbc.androidlauncher.data.system

import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.os.BatteryManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SystemRepositoryImpl(
    private val context: Context
): SystemRepository {

    // igual que en el app repository
    // asignar a _batteryLevel el valor de getBatteryLevel
    private val _batteryLevel = MutableStateFlow(getBatteryLevel())
    override val batteryLevel = _batteryLevel.asStateFlow()

    // actualizar el valor de _batteryLevel con la funcion de abajo
    override fun updateBatteryLevel() {
        _batteryLevel.value = getBatteryLevel()
    }

    // obtener el nivel de bateria con BatteryManager
    override fun getBatteryLevel(): Int {
        val bm = context.getSystemService(BATTERY_SERVICE) as BatteryManager
        return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    }
}