package com.jbc.androidlauncher.presentation.applist

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jbc.androidlauncher.data.AppInfo

@Composable
fun AppListScreen(viewModel: AppListViewModel)
{
    val apps by viewModel.apps.collectAsState()
    val context = LocalContext.current

    LazyColumn {
        items(apps) { app ->
            AppItem(app) { launchIntent ->
                context.startActivity(launchIntent)
            }
        }
    }
}

@Composable
fun AppItem(app: AppInfo, onAppClick: (Intent) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onAppClick(app.launchIntent) }
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

























