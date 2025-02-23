package com.jbc.androidlauncher.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jbc.androidlauncher.presentation.applist.AppListViewModel
import com.jbc.androidlauncher.presentation.MainScreen
import com.jbc.androidlauncher.presentation.applist.AppListScreen

@Composable
fun NavigationWrapper() {

    val appListViewModel: AppListViewModel = viewModel()

    val navController = rememberNavController()

    NavHost(navController, startDestination = Main) {
        composable<Main> {
            MainScreen() { navController.navigate(AppList) }
        }

        composable<AppList> {
            AppListScreen(appListViewModel)
        }
    }
}