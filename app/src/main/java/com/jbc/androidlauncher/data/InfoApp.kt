package com.jbc.androidlauncher.data

import android.content.Intent

data class InfoApp(
    val name: String,
    val packageName: String,
    val intent: Intent,
)
