package com.jbc.androidlauncher.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.window.DialogProperties
import com.jbc.androidlauncher.R
import com.jbc.androidlauncher.data.AppInfo

@Composable
fun AppDialog(
    app: AppInfo?,
    isAppOnMainScreen: Boolean,
    onDismissRequest: () -> Unit,
    onAddToMain: () -> Unit,
    onRemoveFromMain: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            decorFitsSystemWindows = false
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
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
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                DialogActionButton(
                    Modifier
                        .height(60.dp)
                        .fillMaxWidth(),
                    painterResource(R.drawable.icon_info),
                    "Información de la app",
                    onClick = {
                        onDismissRequest()
                    }
                )
                // Si la aplicacion esta en el MainScreen mostramos la opcion de eliminar
                // Si la aplicacion no esta en el MainScreen mostramos la opcion de añadir
                if(isAppOnMainScreen) {
                    DialogActionButton(
                        Modifier
                            .height(60.dp)
                            .fillMaxWidth(),
                        painterResource(R.drawable.icon_add_home_screen),
                        "Eliminar de la pantalla de inicio",
                        onClick = {
                            onRemoveFromMain()
                            onDismissRequest()
                        }
                    )
                } else {
                    DialogActionButton(
                        Modifier
                            .height(60.dp)
                            .fillMaxWidth(),
                        painterResource(R.drawable.icon_add_home_screen),
                        "Añadir a la pantalla de inicio",
                        onClick = {
                            onAddToMain()
                            onDismissRequest()
                        }
                    )
                }

                DialogActionButton(
                     Modifier
                         .height(60.dp)
                         .fillMaxWidth(),
                    painterResource(R.drawable.icon_uninstall),
                    "Desinstalar aplicación",
                    onClick = {
                        onDismissRequest()
                    }
                )
            }
        }
    }
}

@Composable
fun DialogActionButton(
    modifier: Modifier,
    painter: Painter,
    text: String,
    onClick: () -> Unit,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .padding(start = 10.dp)
            .clickable { onClick() },
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
















