package com.jbc.androidlauncher.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.jbc.androidlauncher.data.AppInfo
import com.jbc.androidlauncher.data.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel(application: Application): AndroidViewModel(application) {

    private val helper = AppRepository(application.applicationContext)

    private val _mainScreenApps = MutableStateFlow<List<AppInfo>>(emptyList())
    val mainScreenApps: StateFlow<List<AppInfo>> = _mainScreenApps.asStateFlow()

    init {
        loadApps()
    }

    private fun loadApps() {

    }
}