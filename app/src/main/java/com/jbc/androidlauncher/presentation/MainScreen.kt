package com.jbc.androidlauncher.presentation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MainScreen(navigateToList: () -> Unit) {
    Button(onClick = { navigateToList() }) {
        Text("Ir a listado apps")
    }
}