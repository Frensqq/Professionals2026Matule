package com.example.matule_2026

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.matule_2026.DI.networkModule
import com.example.matule_2026.Domain.Repository.UserRepository
import com.example.matule_2026.Presentation.navigate.Navigation
import com.example.matule_2026.Presentation.notification.NotificationService
import com.example.matule_2026.ui.theme.Matule2026Theme
import com.example.networklibrary.network.monitor.AndroidNetworkMonitor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    private val isOnline = MutableStateFlow(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        startKoin {
            androidContext(this@MainActivity)
            modules(networkModule)
        }

        UserRepository.init(this)

        val networkMonitor = AndroidNetworkMonitor(this)
        isOnline.value = networkMonitor.isConnected()

        setContent {
            Matule2026Theme {
                MaterialTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) {
                        Navigation(isOnline.value)
                    }
                }
            }
        }

        lifecycleScope.launch {
            while (true) {
                isOnline.value = networkMonitor.isConnected()
                delay(2000)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        NotificationService.schedule(this)
    }

    override fun onStart() {
        super.onStart()
        NotificationService.cancel(this)
    }
}
