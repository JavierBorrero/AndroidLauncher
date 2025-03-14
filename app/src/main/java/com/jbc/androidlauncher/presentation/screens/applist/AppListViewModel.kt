package com.jbc.androidlauncher.presentation.screens.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbc.androidlauncher.data.AppInfo
import com.jbc.androidlauncher.data.AppRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class AppListViewModel (private val appRep: AppRepository): ViewModel() {

    // Listado apps
    private val _apps = MutableStateFlow<List<AppInfo>>(emptyList())

    // App seleccionada de la lista
    private val _selectedApp = MutableStateFlow<AppInfo?>(null)
    val selectedApp: StateFlow<AppInfo?> = _selectedApp.asStateFlow()

    private val _searchText = MutableStateFlow<String>("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow<Boolean>(false)
    val isSearching: StateFlow<Boolean> = _isSearching.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val apps= searchText
        .debounce(500)
        .combine(_apps) {text, apps ->
            if(text.isBlank()) {
                apps
            } else {
                apps.filter {
                    it.name.contains(text, ignoreCase = true)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _apps.value
        )

    fun loadApps() {
        viewModelScope.launch {
            _isLoading.value = true
            _apps.value = appRep.getAllApps()
            _isLoading.value = false
        }
    }

    // Al mantener presionado guardamos el valor del app
    fun onAppLongPress(app: AppInfo) {
        _selectedApp.value = app
    }

    // Al cerrar el dialog cambiamos a null la app seleccionada
    fun dismissDialog() {
        _selectedApp.value = null
    }

    fun isOnMainScreen(app: AppInfo): Boolean {
        return appRep.isAppOnMainScreen(app)
    }

    fun onAddToMain() {
        appRep.addAppToMainScreen(_selectedApp.value!!)
    }

    fun onRemoveFromMain() {
        appRep.removeAppFromMainScreen(_selectedApp.value!!)
    }

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }

    fun setSearch(value: Boolean) {
        _isSearching.value = value
        if(!value) _searchText.value = ""
    }
}