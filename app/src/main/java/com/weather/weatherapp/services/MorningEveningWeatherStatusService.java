package com.weather.weatherapp.services;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;

import com.weather.weatherapp.R;
import com.weather.weatherapp.activities.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MorningEveningWeatherStatusService extends Service {
    private String CHANNEL_ID = "NOTIFICATION_CHANNEL";
    private NotificationManager notificationManager;
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


    @SuppressLint({"StaticFieldLeak", "ResourceType"})
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        barStatus = intent.getBooleanExtra("barStatus", false);

        if (barStatus) {
            int smallIcon = R.drawable.ic_sun;

            // Get the layouts to use in the custom notification
            RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.notification_small);
            RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.weather_notification_view);

            //convert text to bitmap
            Bitmap bitmap = createBitmapFromString("14°");

            Intent mIntent = new Intent(this, MainActivity.class);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mIntent.putExtra("notificationId", NOTIFICATION_ID);

            PendingIntent mainIntent = PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_ONE_SHOT);

            // Apply the layouts to the notification.
            NotificationCompat.Builder customNotification = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                customNotification = new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(smallIcon)
                        .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                        .setCustomContentView(notificationLayout)
                        .setCustomBigContentView(notificationLayoutExpanded)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setSound(null)
                        .setAutoCancel(true)
                        .setSilent(true)
                        .setContentIntent(mainIntent);
            }
            Notification notification = customNotification.build();
            notificationManager.notify(NOTIFICATION_ID,notification);
        } else {
            dismissNotification();
        }

        return START_NOT_STICKY;
    }

    private Bitmap createBitmapFromString(String inputNumber) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(100);
        paint.setTextAlign(Paint.Align.CENTER);

        Rect textBounds = new Rect();
        paint.getTextBounds(inputNumber, 0, inputNumber.length(), textBounds);

        Bitmap bitmap = Bitmap.createBitmap(textBounds.width() + 10, 90,
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawText(inputNumber, textBounds.width() / 2 + 5, 70, paint);
        return bitmap;
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
