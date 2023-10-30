package com.weather.weatherapp;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.weather.weatherapp.utils.SharedPreferenceUtils;

public class MyApp extends Application {
    SharedPreferenceUtils preferenceUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        preferenceUtils = SharedPreferenceUtils.getInstance(getApplicationContext());

        String theme = preferenceUtils.getAppTheme();
        updateTheme(theme);
    }

    public void updateTheme(String theme) {
        int themeMode = 0;
        if (theme.equalsIgnoreCase(SharedPreferenceUtils.THEME_DAY)) {
            themeMode = AppCompatDelegate.MODE_NIGHT_NO;
        } else if (theme.equalsIgnoreCase(SharedPreferenceUtils.THEME_NIGHT)) {
            themeMode = AppCompatDelegate.MODE_NIGHT_YES;
        } else {
            themeMode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
        }
        AppCompatDelegate.setDefaultNightMode(themeMode);
    }

}
