package com.weather.weatherapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferenceUtils {

    private static SharedPreferenceUtils mSharedPreferenceUtils;
    protected Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedPreferencesEditor;


    private static final String PREF_THEME = "PREF_THEME";
    public static final String THEME_DAY = "theme_day";
    public static final String THEME_NIGHT = "theme_night";
    public static final String THEME_AUTO = "theme_auto";

    private String APP_PREFERENCES = "weather";
    private String IS_FIRST = "isFirst";

    private String NOTIFICATION_BAR = "isFirst";

    public static synchronized SharedPreferenceUtils getInstance(Context context) {
        try {
            if (mSharedPreferenceUtils == null) {
                mSharedPreferenceUtils = new SharedPreferenceUtils(context.getApplicationContext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mSharedPreferenceUtils;
    }

    private SharedPreferenceUtils(Context context) {
        mContext = context;
        mSharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mSharedPreferencesEditor = mSharedPreferences.edit();
    }

    public void setAppTheme(String theme) {
        mSharedPreferencesEditor.putString(PREF_THEME, theme);
        mSharedPreferencesEditor.apply();
        mSharedPreferencesEditor.commit();
    }

    public String getAppTheme() {
        return mSharedPreferences.getString(PREF_THEME, THEME_DAY);
    }

    public void setIsNotFirst(boolean isBeforeAppear) {
        mSharedPreferencesEditor.putBoolean(IS_FIRST, isBeforeAppear);
        mSharedPreferencesEditor.apply();
        mSharedPreferencesEditor.commit();
    }

    public boolean isNotFirst() {
        return mSharedPreferences.getBoolean(IS_FIRST, false);
    }

    public void setNotificationBarStatus(boolean isBarActive) {
        mSharedPreferencesEditor.putBoolean(NOTIFICATION_BAR, isBarActive);
        mSharedPreferencesEditor.apply();
        mSharedPreferencesEditor.commit();
    }

    public boolean isNotificationBarActive() {
        return mSharedPreferences.getBoolean(NOTIFICATION_BAR, false);
    }
}
