package com.jbc.androidlauncher.presentation

import androidx.lifecycle.ViewModel
import com.jbc.androidlauncher.data.AppRepository
import com.jbc.androidlauncher.data.InfoApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LauncherScreenViewModel @Inject constructor(private val appRepository: AppRepository): ViewModel(){

    private val initialApps= MutableStateFlow<List<InfoApp>>(emptyList())

    val apps: StateFlow<List<InfoApp>> = initialApps

    init {
        loadApps()
    }

    private fun loadApps() {
        initialApps.value = appRepository.getAllApps()
    }

}