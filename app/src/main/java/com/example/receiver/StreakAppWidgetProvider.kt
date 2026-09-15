package com.example.receiver

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.MainActivity
import com.example.R
import com.example.data.db.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class StreakAppWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // Fetch current streak from database and update views
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getInstance(context)
            val streakEntity = db.appDao().getStreakSync()
            val todayEpoch = LocalDate.now().toEpochDay()
            val streak = streakEntity?.currentStreak ?: 0
            val isPracticedToday = (streakEntity?.lastActiveEpochDay == todayEpoch) &&
                    ((streakEntity?.quizzesCompletedToday ?: 0) > 0)

            for (appWidgetId in appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId, streak, isPracticedToday)
            }
        }
    }

    companion object {
        const val EXTRA_START_DAILY_QUIZ = "extra_start_daily_quiz"

        fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int,
            streak: Int,
            isPracticedToday: Boolean
        ) {
            val views = RemoteViews(context.packageName, R.layout.widget_streak)

            val streakText = if (streak == 1) "1 Day" else "$streak Days"
            views.setTextViewText(R.id.widget_streak_count, streakText)

            val statusText = if (isPracticedToday) {
                "✓ Done today! Streak saved"
            } else {
                "⚡ Tap to keep streak alive!"
            }
            views.setTextViewText(R.id.widget_streak_status, statusText)

            // Intent to launch MainActivity with daily quiz flag
            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                putExtra(EXTRA_START_DAILY_QUIZ, true)
            }
            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            views.setOnClickPendingIntent(R.id.widget_root, pendingIntent)
            views.setOnClickPendingIntent(R.id.widget_btn_quiz, pendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        fun updateAllWidgets(context: Context, streak: Int, isPracticedToday: Boolean) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val componentName = ComponentName(context, StreakAppWidgetProvider::class.java)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(componentName)
            for (appWidgetId in appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId, streak, isPracticedToday)
            }
        }
    }
}
