package com.jbc.androidlauncher.presentation.applist

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jbc.androidlauncher.data.AppInfo
import com.jbc.androidlauncher.presentation.components.AppDialog
import com.jbc.androidlauncher.ui.theme.BackgroundGrey

@Composable
fun AppListScreen(viewModel: AppListViewModel) {

    // Lista de apps
    val apps by viewModel.apps.collectAsState()

    // Aplicacion seleccionada al mantener
    val selectedApp by viewModel.selectedApp.collectAsState()

    val context = LocalContext.current

    // Si hay selected app mostrar el dialog
    if(selectedApp != null){
        AppDialog(selectedApp!!, onDismissRequest = { viewModel.dismissDialog() })
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundGrey)
                .padding(start = 10.dp, top = 16.dp),
            text = "Aplicaciones",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .background(BackgroundGrey)
        ) {
            items(apps) { app ->
                AppItem(
                    app,
                    onAppClick = { launchIntent -> context.startActivity(launchIntent) },
                    onLongPress = { viewModel.onAppLongPress(app) }
                )
            }
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
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
//        Image(
//            painter = BitmapPainter(app.icon),
//            contentDescription = "icon",
//            modifier = Modifier.size(24.dp),
//            colorFilter = ColorFilter.tint(Color.White)
//        )
        Text(
            text = app.name,
            modifier = Modifier.weight(1f),
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
    }
}























