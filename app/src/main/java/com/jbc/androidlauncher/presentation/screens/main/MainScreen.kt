package com.jbc.androidlauncher.presentation.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.TabRow
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
    val clockIntent = mainScreenViewModel.clockIntent
    val calendarIntent = mainScreenViewModel.calendarIntent

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
            batteryLevel,
            formatedTime,
            onClockClick = { context.startActivity(clockIntent) },
            onCalendarClick = { context.startActivity(calendarIntent) }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundGrey)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp),
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

@Composable
fun SystemInfo(
    batLevel: Int,
    fTime: List<String>,
    onClockClick: () -> Unit,
    onCalendarClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundGrey)
            .padding(top = 36.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .padding(start = 10.dp)
                .clickable { onClockClick() },
            fontSize = 32.sp,
            text = fTime[0],
            color = Color.White,
        )
        Text(
            modifier = Modifier
                .clickable { onCalendarClick() },
            fontSize = 32.sp,
            text = fTime[1],
            color = Color.White
        )
        Text(
            modifier = Modifier
                .padding(end = 10.dp),
            fontSize = 32.sp,
            text = "$batLevel%",
            color = Color.White,
        )
    }
}