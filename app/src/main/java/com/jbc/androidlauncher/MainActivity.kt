package com.jbc.androidlauncher

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build
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
import com.jbc.androidlauncher.data.AppRepository
import com.jbc.androidlauncher.presentation.LauncherScreen
import com.jbc.androidlauncher.presentation.LauncherScreenViewModel
import com.jbc.androidlauncher.ui.theme.AndroidLauncherTheme
import java.util.stream.Stream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        fun abrirApp(): String {
////            val pm = packageManager
////            try {
////                pm.getPackageInfo(packageName, 0)
////                val intent = pm.getLaunchIntentForPackage(packageName)
////                if (intent != null) {
////                    startActivity(intent)
////                } else {
////                    Toast.makeText(this, "No se pudo abrir la aplicación", Toast.LENGTH_SHORT).show()
////                }
////            } catch (e: PackageManager.NameNotFoundException) {
////                Toast.makeText(this, "La aplicación no está instalada", Toast.LENGTH_SHORT).show()
////            }
//
////            val pm = packageManager
////            val mainIntent = Intent(Intent.ACTION_MAIN)
////            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
////
////            val resolvedInfos = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
////                pm.queryIntentActivities(mainIntent, PackageManager.ResolveInfoFlags.of(0L))
////            } else {
////                pm.queryIntentActivities(mainIntent, 0)
////            }
//
//            val pm = packageManager
//            try {
//                pm.getPackageInfo(packageName, 0)
//                val intent = pm.getLaunchIntentForPackage(packageName)
//                if (intent != null) {
//                    startActivity(intent)
//                } else {
//                    Toast.makeText(this, "No se pudo abrir la aplicación", Toast.LENGTH_SHORT).show()
//                }
//            } catch (e: PackageManager.NameNotFoundException) {
//                Toast.makeText(this, "La aplicación no está instalada", Toast.LENGTH_SHORT).show()
//            }
//
//            val packagesAll: List<ApplicationInfo> = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
//
//            val packagesByUser: List<ApplicationInfo> = packagesAll
//                .filter { (it.flags and ApplicationInfo.FLAG_SYSTEM) == 0 }
//                .map { ApplicationInfo(it) }
//
//
//
//        }
//
//        abrirApp()

        setContent {
            LauncherScreen(LauncherScreenViewModel(AppRepository(packageManager))) //abrirApp("com.android.settings")
        }
    }
}