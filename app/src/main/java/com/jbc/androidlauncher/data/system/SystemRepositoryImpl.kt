package com.jbc.androidlauncher.data.system

import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.os.BatteryManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SystemRepositoryImpl(
    private val context: Context
): SystemRepository {

    // obtener el nivel de bateria con BatteryManager
    override fun getBatteryLevel(): Int {
        val bm = context.getSystemService(BATTERY_SERVICE) as BatteryManager
        return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    }

    // obtener fecha actual, formatear y devolver fecha
    override fun getSystemDate(): String {
        val date = Date()
        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        val formattedTime = formatter.format(date)

        return formattedTime
    }
}