package com.jbc.androidlauncher.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jbc.androidlauncher.data.AppInfo

@Composable
fun AppDialog(
    app: AppInfo?,
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = "Opciones de ${app?.name}") },
        text = { Text("¿Qué quieres hacer con esta app?") },
        confirmButton = {
            Button(onClick = onDismissRequest) {
                Text("Cerrar")
            }
        }
    )


//    Dialog(onDismissRequest = { onDismissRequest() }) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(400.dp)
//                .padding(8.dp),
//            shape = RoundedCornerShape(16.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally,
//            ) {
//                Text("Cadena 1")
//                Text("Cadena 2")
//                Text("Cadena 3")
//            }
//        }
//    }
}