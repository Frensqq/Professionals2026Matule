package com.example.matule_2026

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.matule_2026.DI.networkModule
import com.example.matule_2026.Domain.Repository.UserRepository
import com.example.matule_2026.Presentation.navigate.Navigation
import com.example.matule_2026.Presentation.notification.AlarmBroadcastReceiver
import com.example.matule_2026.Presentation.notification.NotificationService
import com.example.matule_2026.ui.theme.Matule2026Theme
import com.example.networklibrary.network.monitor.AndroidNetworkMonitor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    private lateinit var networkState: AndroidNetworkMonitor
    private val _isOnline = MutableStateFlow(false)
    private val notificationPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        startKoin {
            androidContext(this@MainActivity)
            modules(networkModule)
        }

        networkState = AndroidNetworkMonitor(this)
        _isOnline.value = networkState.isConnected()

        UserRepository.init(this)


            NotificationService.createNotificationChannel(this)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) !=
                    PackageManager.PERMISSION_GRANTED
                ) {
                    notificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }



        setContent {
            val isOnline by _isOnline.collectAsState()

            Matule2026Theme {
                MaterialTheme{
                    Scaffold(modifier = Modifier.fillMaxSize()) {
                        Navigation(isOnline)
                    }
                }
            }
        }

        lifecycleScope.launch {
            while (true) {
                _isOnline.value = networkState.isConnected()
                kotlinx.coroutines.delay(2000)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        scheduleNotification()
    }

    override fun onStart() {
        super.onStart()
        cancelNotification()
    }

    private fun scheduleNotification() {
        val alarmManager = getSystemService(AlarmManager::class.java)
        val intent = Intent(this, AlarmBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )



        val triggerTime = SystemClock.elapsedRealtime() + 60000/3

        if (UserRepository.notification) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            } else {
                alarmManager.setExact(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            }
        }
    }

    private fun cancelNotification() {
        val alarmManager = getSystemService(AlarmManager::class.java)
        val intent = Intent(this, AlarmBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }
}

