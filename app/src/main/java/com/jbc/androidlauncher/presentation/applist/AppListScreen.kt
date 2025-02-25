package com.jbc.androidlauncher.presentation.applist

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jbc.androidlauncher.data.AppInfo
import com.jbc.androidlauncher.presentation.components.AppDialog

@Composable
fun AppListScreen(viewModel: AppListViewModel = viewModel()) {
    val apps by viewModel.apps.collectAsState()
    val context = LocalContext.current

    val selectedApp by viewModel.selectedApp.collectAsState()

    if(selectedApp != null){
        AppDialog(selectedApp!!, onDismissRequest = { viewModel.dismissDialog() })
    }

    LazyColumn {
        items(apps) { app ->
            AppItem(
                app,
                onAppClick = { launchIntent -> context.startActivity(launchIntent) },
                onLongPress = { viewModel.onAppLongPress(app) }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppItem(
    app: AppInfo,
    onAppClick: (Intent) -> Unit,
    onLongPress: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = { onAppClick(app.launchIntent) },
                onLongClick = {
                    onLongPress()
                },
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = app.name,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
        )
    }
}

























