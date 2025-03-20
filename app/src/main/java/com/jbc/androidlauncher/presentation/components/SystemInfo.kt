package com.jbc.androidlauncher.presentation.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.jbc.androidlauncher.R
import com.jbc.androidlauncher.service.LockAccessibilityService
import com.jbc.androidlauncher.ui.theme.BackgroundGrey

@Composable
fun SystemInfo(
    context: Context,
    batLevel: Int,
    fTime: List<String>,
    intentMap: Map<String, Intent?>,
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .background(BackgroundGrey)
            .padding(top = 36.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 10.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .clickable { context.startActivity(intentMap["clock"]) },
                fontSize = 32.sp,
                text = fTime[0],
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .clickable { context.startActivity(intentMap["calendar"]) },
                fontSize = 30.sp,
                text = fTime[1],
                color = Color.White
            )
        }

        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .clickable {  },
                fontSize = 32.sp,
                text = "${batLevel}%",
                color = Color.White
            )
            // nada de momento
            Text(
                modifier = Modifier
                    .clickable {  },
                fontSize = 30.sp,
                text = "",
                color = Color.White
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 10.dp),
        ) {
            Row {
                IconButton(onClick = { context.startActivity(intentMap["settings"]) }) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(R.drawable.icon_settings),
                        contentDescription = "icon",
                        tint = Color.White
                    )
                }

                IconButton(onClick = {
                    val intent = Intent(context, LockAccessibilityService::class.java)
                    ContextCompat.startForegroundService(context, intent) }) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(R.drawable.icon_power),
                        contentDescription = "icon",
                        tint = Color.White
                    )
                }
            }

            Row {
                IconButton(onClick = {}) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(R.drawable.icon_add),
                        contentDescription = "icon",
                        tint = Color.White
                    )
                }

                IconButton(onClick = {}) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(R.drawable.icon_add),
                        contentDescription = "icon",
                        tint = Color.White
                    )
                }
            }
        }
    }
}