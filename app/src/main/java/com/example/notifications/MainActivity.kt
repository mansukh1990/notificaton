package com.example.notifications

import android.accessibilityservice.MagnificationConfig
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService

val CHANNEL_ID = "channelId"
val CHANNEL_NAME = "channelName"
val notificationId = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()
        //Pending Intent
        val intent = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_MUTABLE)


        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("30 Days of App Dev Tutoraial 27")
            .setContentText("Congratulations for showing up today")
            .setSmallIcon(R.drawable.baseline_insert_emoticon_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setColor(R.color.black)
            .build()

        val notificationManager = NotificationManagerCompat.from(this)
        val btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener {
            notificationManager.notify(notificationId, notification)
            }
        }

    fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {

                description = "This is my notification channel"

            }
            val manager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}