package com.jbc.androidlauncher.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.jbc.androidlauncher.R
import com.jbc.androidlauncher.data.AppInfo

@Composable
fun AppDialog(
    app: AppInfo?,
    onDismissRequest: () -> Unit,
) {

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Gray
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "${app?.name}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
            ) {
                DialogActionButton(
                    Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    painterResource(R.drawable.icon_info),
                    "Información de la app",
                )
                DialogActionButton(
                     Modifier
                         .weight(1f)
                         .fillMaxWidth(),
                    painterResource(R.drawable.icon_uninstall),
                    "Desinstalar aplicación",
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DialogActionButton(
    modifier: Modifier,
    painter: Painter,
    text: String,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .combinedClickable(
                onClick = {

                },
                onLongClick = {

                }
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painter,
            contentDescription = "icon",
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )

        Text(
            text = text,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 10.dp),
        )
    }
}
















