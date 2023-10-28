package com.weather.weatherapp;

import android.app.Application;
import android.content.Intent;

import com.weather.weatherapp.utils.SharedPreferenceUtils;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
       /* Intent alternativeIntent;
        if (!SharedPreferenceUtils.getInstance(getApplicationContext()).isFirst()) {
            alternativeIntent = new Intent(this, MainActivity2.class);
        } else {
            alternativeIntent = new Intent(this, MainActivity.class);
        }
        alternativeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(alternativeIntent);
    */}
}
