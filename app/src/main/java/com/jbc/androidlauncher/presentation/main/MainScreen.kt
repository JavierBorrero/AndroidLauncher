package com.jbc.androidlauncher.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jbc.androidlauncher.presentation.applist.AppItem
import com.jbc.androidlauncher.ui.theme.BackgroundGrey

@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel,
    navigateToList: () -> Unit
) {

    val apps by mainScreenViewModel.mainScreenApps.collectAsState()

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundGrey)
                .padding(start = 10.dp, top = 36.dp),
            onClick = { navigateToList() }
        ) {
            Text("Ir a listado apps")
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