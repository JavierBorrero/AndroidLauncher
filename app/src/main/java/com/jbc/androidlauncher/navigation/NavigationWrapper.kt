package com.jbc.androidlauncher.navigation

import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jbc.androidlauncher.data.AppRepository
import com.jbc.androidlauncher.presentation.LauncherScreen
import com.jbc.androidlauncher.presentation.LauncherScreenViewModel
import com.jbc.androidlauncher.presentation.MainScreen

@Composable
fun NavigationWrapper() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = Main) {
        composable<Main> {
            MainScreen() { navController.navigate(AppList) }
        }

        composable<AppList> {
            val context = LocalContext.current
            val packageManager = context.packageManager
            LauncherScreen(LauncherScreenViewModel(AppRepository(packageManager)))
        }
    }

}