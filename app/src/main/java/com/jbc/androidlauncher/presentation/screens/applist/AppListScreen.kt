package com.jbc.androidlauncher.presentation.screens.applist

import android.content.Intent
import android.graphics.Bitmap
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.jbc.androidlauncher.R
import com.jbc.androidlauncher.data.AppInfo
import com.jbc.androidlauncher.presentation.components.AppDialog
import com.jbc.androidlauncher.ui.theme.BackgroundGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppListScreen(appListViewModel: AppListViewModel) {

    // Lista de apps
    val apps by appListViewModel.apps.collectAsState()

    // Aplicacion seleccionada al mantener
    val selectedApp by appListViewModel.selectedApp.collectAsState()

    // Buscador
    val searchText by appListViewModel.searchText.collectAsState()
    val isSearching by appListViewModel.isSearching.collectAsState()

    val context = LocalContext.current

    // Si hay selected app mostrar el dialog
    if(selectedApp != null){
        AppDialog(
            selectedApp!!,
            onDismissRequest = { appListViewModel.dismissDialog() },
            onAddToMain = { appListViewModel.onAddToMain() })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGrey)
    ) {

        TopAppBar(
            modifier = Modifier
                .padding(top = 18.dp),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = BackgroundGrey,
            ),
            title = {
                if(isSearching) {
                    TextField(
                        value = searchText,
                        onValueChange = appListViewModel::onSearchTextChanged,
                        placeholder = { Text("Buscar...") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Color.White),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = BackgroundGrey,
                            cursorColor = Color.White
                        )
                    )
                } else {
                    Text(
                        fontSize = 30.sp,
                        text = "Aplicaciones",
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            actions = {

                if(isSearching) {
                    IconButton(onClick = { appListViewModel.setSearch(false) }) {
                        Image(
                            painter = painterResource(R.drawable.icon_arrow_back),
                            contentDescription = "icon",
                            modifier = Modifier.size(34.dp)
                        )
                    }
                } else {
                    IconButton(onClick = { appListViewModel.setSearch(true) }) {
                        Image(
                            painter = painterResource(R.drawable.icon_search),
                            contentDescription = "icon",
                            modifier = Modifier.size(34.dp)
                        )
                    }
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .background(BackgroundGrey)
        ) {
            items(apps) { app ->
                AppItem(
                    app,
                    onAppClick = { launchIntent -> context.startActivity(launchIntent) },
                    onLongPress = { appListViewModel.onAppLongPress(app) }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppItem(
    app: AppInfo,
    onAppClick: (Intent) -> Unit,
    onLongPress: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = { onAppClick(app.launchIntent) },
                onLongClick = {
                    onLongPress()
                },
            )
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            app.icon.toBitmap(config = Bitmap.Config.ARGB_8888).asImageBitmap(),
            contentDescription = "icon",
            modifier = Modifier.size(32.dp),
        )
        Text(
            text = app.name,
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
    }
}