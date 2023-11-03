package com.weather.weatherapp.services;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WeatherUpdateWorker extends Worker {
    private Context context;

    public WeatherUpdateWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }


    @Override
    public Result doWork() {
        try {
            Log.i("WeatherUpdateWorker", "WorkManager task is running.");

            Intent intent = new Intent(context, CurrentWeatherStatusService.class);
            intent.putExtra("barStatus", true);
            context.startService(intent);

            return Result.success();
        } catch (Exception e) {
            Log.i("WeatherUpdateWorker", "Error in doWork: " + e.getMessage());
            return Result.failure();
        }
    }
}

