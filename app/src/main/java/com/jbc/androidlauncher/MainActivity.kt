package com.jbc.androidlauncher

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jbc.androidlauncher.presentation.LauncherScreen
import com.jbc.androidlauncher.ui.theme.AndroidLauncherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        fun abrirApp(packageName: String) {
            val pm = packageManager
            try {
                pm.getPackageInfo(packageName, 0)
                val intent = pm.getLaunchIntentForPackage(packageName)
                if (intent != null) {
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "No se pudo abrir la aplicación", Toast.LENGTH_SHORT).show()
                }
            } catch (e: PackageManager.NameNotFoundException) {
                Toast.makeText(this, "La aplicación no está instalada", Toast.LENGTH_SHORT).show()
            }
        }

        setContent {
            LauncherScreen { abrirApp("com.android.settings") }
        }
    }
}