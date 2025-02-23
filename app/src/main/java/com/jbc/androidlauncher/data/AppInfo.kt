package com.jbc.androidlauncher.data

import android.content.Intent
import android.graphics.drawable.Drawable

data class AppInfo(
    val name: String,
    val icon: Drawable,
    val launchIntent: Intent,
)
