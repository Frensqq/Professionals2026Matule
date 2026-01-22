package com.example.matule_2026.Presentation.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.uikit.R

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            NotificationCompat.Builder(it, "reminder")
                .setSmallIcon(R.drawable.home)
                .setContentTitle("Возвращайтесь скорее!")
                .build()
                .also { notification ->
                    NotificationManagerCompat.from(it).notify(1, notification)
                }
        }
    }
}