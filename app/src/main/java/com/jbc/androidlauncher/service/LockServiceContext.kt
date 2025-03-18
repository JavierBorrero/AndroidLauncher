package com.jbc.androidlauncher.service

import android.content.Context
import android.content.Intent
import android.os.Build

fun Context.lockScreenAccessibility(){
    val intentService = Intent(this, LockAccessibilityService::class.java)
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        this.startForegroundService(intentService)
    } else {
        this.startActivity(intentService)
    }
}