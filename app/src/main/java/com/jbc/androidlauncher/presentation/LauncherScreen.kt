package com.jbc.androidlauncher.presentation

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jbc.androidlauncher.data.InfoApp

@Composable
fun LauncherScreen(viewModel: LauncherScreenViewModel)
{
    val context = LocalContext.current
    val appList by viewModel.apps.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(appList) { app ->
            AppItem(app, context)
        }
    }
}

@Composable
fun AppItem(app: InfoApp, context: Context) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = app.name,
            modifier = Modifier.weight(1f)
                .clickable {
                    val intent = context.packageManager.getLaunchIntentForPackage(app.packageName)
                    context.startActivity(intent)
                },
            fontSize = 16.sp,
        )
    }
}

























