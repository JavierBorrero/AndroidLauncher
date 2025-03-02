package com.jbc.androidlauncher.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbc.androidlauncher.data.AppInfo
import com.jbc.androidlauncher.data.AppRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(private val appRep: AppRepository): ViewModel() {

    val mainScreenApps: StateFlow<List<AppInfo>> = appRep.mainScreenApps

    init {
        viewModelScope.launch {
            appRep.mainScreenApps.collect { apps ->
                Log.d("MAINSCREENVIEWMODEL", "${apps.size} apps")
            }
        }
    }

}