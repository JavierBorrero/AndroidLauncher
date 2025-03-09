package com.jbc.androidlauncher.presentation.preview

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.jbc.androidlauncher.ui.theme.BackgroundGrey


@Preview
@Composable
fun Preview() {

    var text by remember { mutableStateOf("hello") }

    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text("Search") },
        singleLine = true,
        textStyle = TextStyle(color = Color.White),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = BackgroundGrey,
            cursorColor = Color.White,

        )
    )
}

