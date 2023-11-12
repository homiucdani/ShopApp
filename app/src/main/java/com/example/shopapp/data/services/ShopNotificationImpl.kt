package com.example.shopapp.data.services

import android.app.Application
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.shopapp.R
import com.example.shopapp.core.util.Constants
import com.example.shopapp.domain.services.ShopNotification
import com.example.shopapp.MainActivity

class ShopNotificationImpl(
    private val app: Application
) : ShopNotification {

    private val notificationManager =
        app.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override fun addToCartNotification(productName: String, message: String) {
        val openMainActivity = Intent(app, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            app,
            1,
            openMainActivity,
            PendingIntent.FLAG_IMMUTABLE
        )
        val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(app, Constants.SHOP_CHANNEL_ID)
                .setSmallIcon(R.drawable.cart)
                .setContentTitle("$productName was added!")
                .setContentText(message)
                .addAction(
                    Notification.Action(R.drawable.cart, "Shop", pendingIntent)
                )
                .build()
        } else {
            Notification.Builder(app)
                .setSmallIcon(R.drawable.cart)
                .setContentTitle("$productName was added!")
                .setContentText(message)
                .build()
        }

        notificationManager.notify(
            1,
            notification
        )
    }
}