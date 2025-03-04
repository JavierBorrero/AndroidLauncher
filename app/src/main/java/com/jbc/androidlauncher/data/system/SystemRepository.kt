package com.jbc.androidlauncher.data.system

interface SystemRepository {

    // obtener nivel de bateria con BatteryManager
    fun getBatteryLevel(): Int

    // obtener fecha actual
    fun getSystemDate(): String
}