package com.jbc.androidlauncher.service

import android.accessibilityservice.AccessibilityService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.view.accessibility.AccessibilityEvent
import androidx.core.app.NotificationCompat
import com.jbc.androidlauncher.R

class LockAccessibilityService(): AccessibilityService() {

    override fun onCreate() {
        super.onCreate()
        startForegroundService()
    }

    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {}

    override fun onInterrupt() {}

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        startForegroundService()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)
        }

        return START_STICKY
    }

    private fun startForegroundService() {
        val channelId = "lock_service_channel"
        val channelName = "Lock Service"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(NotificationManager::class.java)
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Lock Service")
            .setContentText("Service is running...")
            .setSmallIcon(R.drawable.icon_power) // Usa un icono válido
            .build()

        startForeground(1, notification)
    }
}