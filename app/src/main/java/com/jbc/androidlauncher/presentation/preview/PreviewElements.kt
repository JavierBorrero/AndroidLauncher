package com.jbc.androidlauncher.presentation.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jbc.androidlauncher.ui.theme.BackgroundGrey

@Preview
@Composable
fun MainScreen(
    //mainScreenViewModel: MainScreenViewModel,
    //navigateToList: () -> Unit
) {

    //val apps by mainScreenViewModel.mainScreenApps.collectAsState()

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundGrey)
                .padding(start = 10.dp, top = 36.dp),
            onClick = {  }
        ) {
            Text("Ir a listado apps")
        }

        SystemInfo()

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(BackgroundGrey)
        ) {
            items(20) {
                Text("texto")
            }
        }
    }
}

@Composable
fun SystemInfo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundGrey),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .padding(start = 10.dp),
            fontSize = 32.sp,
            text = "20:42",
            color = Color.White,
        )
        Text(
            modifier = Modifier
                .padding(start = 24.dp, top = 10.dp),
            fontSize = 18.sp,
            text = "49%",
            color = Color.White,
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundGrey),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(start = 26.dp),
            fontSize = 18.sp,
            text = "03/03",
            color = Color.White
        )
    }
}