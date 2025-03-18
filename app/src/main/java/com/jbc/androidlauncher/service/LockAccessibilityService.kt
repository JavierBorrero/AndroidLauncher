package com.jbc.androidlauncher.service

import android.accessibilityservice.AccessibilityService
import android.app.Service
import android.content.Intent
import android.os.Build
import android.view.accessibility.AccessibilityEvent

class LockAccessibilityService(): AccessibilityService() {

    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {}

    override fun onInterrupt() {}

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)
        }

        return Service.START_STICKY
    }
}