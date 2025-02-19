package com.jbc.androidlauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jbc.androidlauncher.data.AppRepository
import com.jbc.androidlauncher.navigation.NavigationWrapper
import com.jbc.androidlauncher.presentation.LauncherScreen
import com.jbc.androidlauncher.presentation.LauncherScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NavigationWrapper()
//            LauncherScreen(LauncherScreenViewModel(AppRepository(packageManager)))
        }
    }
}