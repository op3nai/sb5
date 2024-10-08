package com.example.rokuremote

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class RokuRemoteWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        appWidgetIds.forEach { appWidgetId ->
            val views = RemoteViews(context.packageName, R.layout.widget_roku_remote)

            // Set up button click intents
            setButtonClickIntent(context, views, R.id.widget_btn_power, "PowerOff")
            setButtonClickIntent(context, views, R.id.widget_btn_home, "Home")
            setButtonClickIntent(context, views, R.id.widget_btn_up, "Up")
            setButtonClickIntent(context, views, R.id.widget_btn_down, "Down")
            setButtonClickIntent(context, views, R.id.widget_btn_left, "Left")
            setButtonClickIntent(context, views, R.id.widget_btn_right, "Right")
            setButtonClickIntent(context, views, R.id.widget_btn_ok, "Select")

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    private fun setButtonClickIntent(context: Context, views: RemoteViews, buttonId: Int, command: String) {
        val intent = Intent(context, RokuRemoteWidget::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            putExtra("command", command)
        }
        val pendingIntent = PendingIntent.getBroadcast(context, buttonId, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        views.setOnClickPendingIntent(buttonId, pendingIntent)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
            val command = intent.getStringExtra("command")
            command?.let {
                // Here you would typically send the command to the Roku device
                // For demonstration, we'll just show a toast
                android.widget.Toast.makeText(context, "Sending command: $it", android.widget.Toast.LENGTH_SHORT).show()
            }
        }
    }
}