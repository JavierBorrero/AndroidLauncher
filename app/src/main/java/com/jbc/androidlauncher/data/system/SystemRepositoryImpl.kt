package com.jbc.androidlauncher.data.system

import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.content.Intent
import android.os.BatteryManager
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

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
    override fun getSystemDate(): List<String> {

        val now = Clock.System.now()
        val dateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())

        val formatedTime = mutableListOf<String>()

        val formatedHour = dateTime.format(
            LocalDateTime.Format {
                hour()
                char(':')
                minute()
            }
        )

        val formatedDate = dateTime.format(
            LocalDateTime.Format {
                dayOfMonth()
                char('/')
                monthNumber()
            }
        )

        formatedTime.add(formatedHour)
        formatedTime.add(formatedDate)

        return formatedTime
    }

    override fun intentsMap(): Map<String, Intent?> {

        val clockIntent = pm.getLaunchIntentForPackage("com.sec.android.app.clockpackage")
        val calendarIntent = pm.getLaunchIntentForPackage("com.samsung.android.calendar")
        val settingsIntent = pm.getLaunchIntentForPackage("com.android.settings")

        val intentMap = mapOf("clock" to clockIntent, "calendar" to calendarIntent, "settings" to settingsIntent)

        return intentMap
    }

//    override fun getClockIntent(): Intent {
//        // nombre de mi paquete de reloj, probar si en otros tlfs funciona
//        val clockIntent = pm.getLaunchIntentForPackage("com.sec.android.app.clockpackage")
//
//        return clockIntent!!
//    }
//
//    override fun getCalendarIntent(): Intent {
//        // nombre de mi paquete de calendario, probar si en otros tlfs funciona
//        val calendarIntent = pm.getLaunchIntentForPackage("com.samsung.android.calendar")
//
//        return calendarIntent!!
//    }
}