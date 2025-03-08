package com.jbc.androidlauncher.data.system

import android.content.Intent
import kotlinx.datetime.LocalDateTime

interface SystemRepository {

    // obtener nivel de bateria con BatteryManager
    fun getBatteryLevel(): Int

    // obtener fecha actual
    fun getSystemDate(): List<String>

    // intent app reloj
    fun getClockIntent(): Intent

    // intent app calendario
    fun getCalendarIntent(): Intent
}