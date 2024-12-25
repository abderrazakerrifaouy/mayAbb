package com.example.myapp2

import android.app.NotificationManager
import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat

class MyJobService : JobService () {
    override fun onStartJob(params: JobParameters?): Boolean {
        createNotification()
        setDarkMode(true)
        jobFinished(params, false)
        return true
    }
    override fun onStopJob(params: JobParameters?): Boolean = false
    private fun createNotification() {
        val notification = NotificationCompat.Builder(this,
            "wifi_channel")
            .setContentTitle("Job Scheduler")
            .setContentText("Wi-Fi connecté !")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()
        val notificationManager =
            getSystemService(NotificationManager::class.java)
        notificationManager.notify(1, notification)
    }

    private fun setDarkMode(enabled: Boolean) {
        val mode = if (enabled) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}
