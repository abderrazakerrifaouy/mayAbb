package com.example.myapp2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        scheduleWifiJob()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private  fun createNotificationChannel(){
        val channel = NotificationChannel(
            "wifi_channel" ,
            "wifi Notifications" ,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun scheduleWifiJob(){
        val componentNam = ComponentName(this , MyJobService::class.java)
        val jobInfo = JobInfo.Builder(1 , componentNam)
            .setRequiresBatteryNotLow(true)
            .build()

        val jobScheduler = getSystemService(JobScheduler::class.java)
        jobScheduler.schedule(jobInfo)
    }
}