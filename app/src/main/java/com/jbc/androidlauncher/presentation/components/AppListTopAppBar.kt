package com.jbc.androidlauncher.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jbc.androidlauncher.R
import com.jbc.androidlauncher.ui.theme.BackgroundGrey

@OptIn(ExperimentalMaterial3Api::class,)
@Composable
fun AppListTopAppBar(
    isSearching: Boolean,
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    onToggleSearch: (Boolean) -> Unit,
) {

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(isSearching) {
        if(isSearching) {
            focusRequester.requestFocus()
        }
    }

    TopAppBar(
        modifier = Modifier
            .padding(top = 18.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BackgroundGrey,
        ),
        title = {
            AnimatedContent(
                targetState = isSearching,
                content = { isSearching ->
                    if (isSearching) {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester),
                            value = searchText,
                            onValueChange = onSearchTextChanged,
                            placeholder = {
                                Text(
                                    text = "Buscar...",
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 20.sp,
                                )
                            },
                            singleLine = true,
                            textStyle = TextStyle(
                                color = Color.White,
                                fontSize = 20.sp,
                                fontFamily = FontFamily.Monospace
                            ),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = BackgroundGrey,
                                unfocusedContainerColor = BackgroundGrey,
                                cursorColor = Color.White,
                            ),
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
                transitionSpec = {
                    slideInHorizontally(initialOffsetX = { 0 }) togetherWith slideOutHorizontally(targetOffsetX = { 0 })
                }
            )
        },
        actions = {
            IconButton(onClick = { onToggleSearch(!isSearching) }) {
                Image(
                    painter = painterResource(
                        if (isSearching) R.drawable.icon_arrow_back else R.drawable.icon_search
                    ),
                    contentDescription = "icon",
                    modifier = Modifier.size(34.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }
    )
}