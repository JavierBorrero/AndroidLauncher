package com.jbc.androidlauncher.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jbc.androidlauncher.ui.theme.BackgroundGrey

@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel,
    navigateToList: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize()
            .background(BackgroundGrey),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Button(
            modifier = Modifier
                .padding(10.dp),
            onClick = { navigateToList() }
        ) {
            Text("Ir a listado apps")
        }

    }
}