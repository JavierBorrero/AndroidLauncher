package com.jbc.androidlauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.jbc.androidlauncher.navigation.NavigationWrapper
import com.jbc.androidlauncher.ui.theme.AndroidLauncherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Esconder StatusBar (barra superior con la hora, bateria, etc)
        val windowsInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowsInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowsInsetsController.hide(WindowInsetsCompat.Type.statusBars())

        setContent {
            AndroidLauncherTheme {
                NavigationWrapper()
            }
        }
    }
}