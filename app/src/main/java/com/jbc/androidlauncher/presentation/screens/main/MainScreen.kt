package com.jbc.androidlauncher.presentation.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jbc.androidlauncher.R
import com.jbc.androidlauncher.presentation.components.SystemInfo
import com.jbc.androidlauncher.presentation.screens.applist.AppItem
import com.jbc.androidlauncher.ui.theme.BackgroundGrey
import kotlinx.coroutines.delay

@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel,
    navigateToList: () -> Unit
) {

    val apps by mainScreenViewModel.mainScreenApps.collectAsState()
    val batteryLevel by mainScreenViewModel.batteryLevel.collectAsState()
    val formatedTime by mainScreenViewModel.systemTime.collectAsState()
    val intentMap = mainScreenViewModel.intentMap

    // actualizar cada 10 segs
    LaunchedEffect(true) {
        while(true) {
            mainScreenViewModel.updateBatteryLevel()
            mainScreenViewModel.updateFormatTime()
            delay(10_000)
        }
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ){

        SystemInfo(
            context,
            batteryLevel,
            formatedTime,
            intentMap
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundGrey),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Tus Aplicaciones",
                fontSize = 24.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            IconButton(onClick = navigateToList) {
                Image(
                    painter = painterResource(R.drawable.icon_add),
                    contentDescription = "icon",
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(BackgroundGrey)
        ) {
            items(apps) { app ->
                AppItem(
                    app,
                    onAppClick = { launchIntent -> context.startActivity(launchIntent) },
                    onLongPress = {  }
                )
            }
        }
    }
}

