package com.weather.weatherapp.services;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.weather.weatherapp.R;

public class NotificationBarService extends Service {
    private String CHANNEL_ID = "NOTIFICATION_CHANNEL";
    private NotificationManager notificationManager;
    private NotificationCompat.Builder notificationCompat;
    private static final int NOTIFICATION_ID = 1;
    private boolean barStatus = false;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        String appName = getString(R.string.app_name);
        NotificationChannel serviceChannel = new NotificationChannel(
                CHANNEL_ID,
                appName,
                NotificationManager.IMPORTANCE_DEFAULT
        );
        serviceChannel.setSound(null, null);
        notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(serviceChannel);
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        barStatus = intent.getBooleanExtra("barStatus", false);

        if (barStatus) {
            int smallIcon = R.drawable.ic_sun;
            // Create notification
            notificationCompat = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Updated report")
                    .setContentText("Weather updated report")
                    .setSmallIcon(smallIcon)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setSound(null)
                    .setSilent(true);

            Notification notification = notificationCompat.build();
            // Start Foreground service
            startForeground(NOTIFICATION_ID, notification);
            // Download the image using Glide
        } else {
            dismissNotification();
        }

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void dismissNotification() {
        stopForeground(true);
        stopSelf();
    }
}
