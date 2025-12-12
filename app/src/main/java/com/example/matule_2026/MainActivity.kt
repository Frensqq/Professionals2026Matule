package com.example.matule_2026

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.matule_2026.ui.theme.Matule2026Theme
import com.example.networklibrary.network.monitor.AndroidNetworkMonitor
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity() {

    private lateinit var networkState: AndroidNetworkMonitor
    private val _isOnline = MutableStateFlow(false)

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        networkState = AndroidNetworkMonitor(this)
        _isOnline.value = networkState.isConnected()


        setContent {
            val isOnline by _isOnline.collectAsState()

            Matule2026Theme {
                MaterialTheme{
                    Scaffold(modifier = Modifier.fillMaxSize()) {

                    }
                }
            }
        }
    }
}

