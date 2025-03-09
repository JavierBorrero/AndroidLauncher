package com.jbc.androidlauncher.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jbc.androidlauncher.MyApp
import com.jbc.androidlauncher.presentation.screens.applist.AppListScreen
import com.jbc.androidlauncher.presentation.screens.applist.AppListViewModel
import com.jbc.androidlauncher.presentation.screens.main.MainScreen
import com.jbc.androidlauncher.presentation.screens.main.MainScreenViewModel
import com.jbc.androidlauncher.presentation.viewModelFactory

@Composable
fun NavigationWrapper() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = Main) {
        composable<Main> {

            val mainScreenViewModel = viewModel<MainScreenViewModel>(
                factory = viewModelFactory {
                    MainScreenViewModel(
                        MyApp.appModule.appRepository,
                        MyApp.appModule.systemRepository,
                    )
                }
            )

            BackHandler {  }

            MainScreen(mainScreenViewModel) { navController.navigate(AppList) }
        }

        composable<AppList> {

            val appListViewModel = viewModel<AppListViewModel>(
                factory = viewModelFactory {
                    AppListViewModel(MyApp.appModule.appRepository)
                }
            )

            AppListScreen(appListViewModel)
        }
    }
}