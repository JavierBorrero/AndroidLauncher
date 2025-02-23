package com.jbc.androidlauncher.presentation.applist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jbc.androidlauncher.data.AppRepository
import com.jbc.androidlauncher.data.AppInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppListViewModel (application: Application): AndroidViewModel(application){

    private val helper = AppRepository(application.applicationContext)

    private val _apps = MutableStateFlow<List<AppInfo>>(emptyList())
    val apps: StateFlow<List<AppInfo>> = _apps.asStateFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            _apps.value = helper.getAllApps()
        }
    }

}