package com.jbc.androidlauncher.data.system

import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.content.Intent
import android.os.BatteryManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SystemRepositoryImpl(
    private val context: Context
): SystemRepository {

    private val pm = context.packageManager

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

    override fun getClockIntent(): Intent {
        // nombre de mi paquete de reloj, probar si en otros tlfs funciona
        val clockIntent = pm.getLaunchIntentForPackage("com.sec.android.app.clockpackage")

        return clockIntent!!
    }

    override fun getCalendarIntent(): Intent {
        // nombre de mi paquete de calendario, probar si en otros tlfs funciona
        val calendarIntent = pm.getLaunchIntentForPackage("com.samsung.android.calendar")

        return calendarIntent!!
    }
}