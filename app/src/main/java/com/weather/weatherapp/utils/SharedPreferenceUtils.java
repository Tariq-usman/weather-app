package com.weather.weatherapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtils {

    private static SharedPreferenceUtils mSharedPreferenceUtils;
    protected Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedPreferencesEditor;

    private String APP_PREFERENCES = "weather";
    private String IS_FIRST = "isFirst";

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

    public void setIsNotFirst(boolean isBeforeAppear) {
        mSharedPreferencesEditor.putBoolean(IS_FIRST, isBeforeAppear);
        mSharedPreferencesEditor.apply();
        mSharedPreferencesEditor.commit();
    }

    public boolean isNotFirst() {
        return mSharedPreferences.getBoolean(IS_FIRST, false);
    }
}
