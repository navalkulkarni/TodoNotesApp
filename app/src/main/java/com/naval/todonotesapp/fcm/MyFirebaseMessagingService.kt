package com.naval.todonotesapp.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.util.JsonToken
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.naval.todonotesapp.R

class MyFirebaseMessagingService : FirebaseMessagingService() {
    val TAG = "Fireball"

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d(TAG,message?.from)
        Log.d(TAG,message?.notification?.body)
        setupNotification(message?.notification?.body)
    }

    private fun setupNotification(body: String?) {
        val channelID = "ToDoNotes"
        val ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notification = NotificationCompat.Builder(this,channelID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentText(body)
                .setSound(ringtone)
                .setContentTitle("Todo Notes App")

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O)
        {
            val channel = NotificationChannel(channelID,"Todo Notes",NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0,notification.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

}