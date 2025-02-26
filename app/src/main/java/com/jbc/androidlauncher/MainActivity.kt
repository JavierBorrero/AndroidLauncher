package com.jbc.androidlauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jbc.androidlauncher.navigation.NavigationWrapper
import com.jbc.androidlauncher.ui.theme.AndroidLauncherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidLauncherTheme {
                NavigationWrapper()
            }
        }
    }
}