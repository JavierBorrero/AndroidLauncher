package com.jbc.androidlauncher.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jbc.androidlauncher.presentation.applist.AppListViewModel
import com.jbc.androidlauncher.presentation.main.MainScreen
import com.jbc.androidlauncher.presentation.applist.AppListScreen
import com.jbc.androidlauncher.presentation.main.MainScreenViewModel

@Composable
fun NavigationWrapper() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = Main) {
        composable<Main> {
            val mainScreenViewModel: MainScreenViewModel = viewModel()

            MainScreen(mainScreenViewModel) { navController.navigate(AppList) }
        }

        composable<AppList> {
            val appListViewModel: AppListViewModel = viewModel()

            AppListScreen(appListViewModel)
        }
    }
}