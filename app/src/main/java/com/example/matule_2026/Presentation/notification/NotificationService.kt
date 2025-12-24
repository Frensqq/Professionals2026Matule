package com.example.matule_2026.Presentation.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.app.NotificationCompat
import com.example.uikit.R

const val CHANNEL_ID = "NotificationAfterMinute"

object NotificationService {
    fun createNotificationChannel(context: Context) {
        val name = "NotificationChannel"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        val notificationManager: NotificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    fun createNotification(context: Context): NotificationCompat.Builder {
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.home)
                .setContentTitle("Возвращайтесь скорее!")
        return builder
    }
}