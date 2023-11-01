package com.weather.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.CompoundButton;

import com.weather.weatherapp.databinding.ActivityNotificationsBinding;
import com.weather.weatherapp.services.CurrentWeatherStatusService;
import com.weather.weatherapp.services.MorEveWeatherUpdateWorker;
import com.weather.weatherapp.services.MorningEveningWeatherStatusService;
import com.weather.weatherapp.services.WeatherUpdateWorker;
import com.weather.weatherapp.utils.SharedPreferenceUtils;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class NotificationsActivity extends AppCompatActivity {
    private ActivityNotificationsBinding binding;

    private SharedPreferenceUtils sharedPreferenceUtils;
    private String workManagerTag = "weather_update";
    private String morningWorkManagerTag = "morning_weather_update";
    private String eveningWorkManagerTag = "evening_weather_update";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        sharedPreferenceUtils = SharedPreferenceUtils.getInstance(this);

        binding.btnBack.setOnClickListener(view -> {
            finish();
        });

        binding.notificationBarSwitch.setChecked(sharedPreferenceUtils.isCurrentWeatherActive());
        binding.notificationBarSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                binding.notificationBarSwitch.setChecked(isChecked);
                sharedPreferenceUtils.setCurrentWeatherStatus(isChecked);
                if (isChecked) {
                    PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(WeatherUpdateWorker.class, 2, TimeUnit.MINUTES).addTag(workManagerTag).build();
                    WorkManager.getInstance(NotificationsActivity.this).enqueue(periodicWorkRequest);
                } else {
                    WorkManager.getInstance(NotificationsActivity.this).cancelAllWorkByTag(workManagerTag);
                    Intent intent = new Intent(NotificationsActivity.this, CurrentWeatherStatusService.class);
                    intent.putExtra("barStatus", isChecked);
                    startService(intent);
                }
            }
        });

        binding.dailyWeatherSwitch.setChecked(sharedPreferenceUtils.isMorEveWeatherActive());
        binding.dailyWeatherSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                binding.dailyWeatherSwitch.setChecked(isChecked);
                sharedPreferenceUtils.setMorEveWeatherStatus(isChecked);
                if (isChecked) {
                    // Calculate the initial delay for the morning task to start at 6 AM every day
                    Calendar morningCalendar = Calendar.getInstance();
                    morningCalendar.set(Calendar.HOUR_OF_DAY, 6);
                    morningCalendar.set(Calendar.MINUTE, 0);
                    morningCalendar.set(Calendar.SECOND, 0);
                    long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
                    long morningTimeInMillis = morningCalendar.getTimeInMillis();
                    long morningInitialDelay = (morningTimeInMillis > currentTimeInMillis) ?
                            morningTimeInMillis - currentTimeInMillis :
                            morningTimeInMillis + (24 * 60 * 60 * 1000) - currentTimeInMillis;

                    // Calculate the initial delay for the evening task to start at 6 PM every day
                    Calendar eveningCalendar = Calendar.getInstance();
                    eveningCalendar.set(Calendar.HOUR_OF_DAY, 18);
                    eveningCalendar.set(Calendar.MINUTE, 0);
                    eveningCalendar.set(Calendar.SECOND, 0);
                    long eveningTimeInMillis = eveningCalendar.getTimeInMillis();
                    long eveningInitialDelay = (eveningTimeInMillis > currentTimeInMillis) ?
                            eveningTimeInMillis - currentTimeInMillis :
                            eveningTimeInMillis + (24 * 60 * 60 * 1000) - currentTimeInMillis;

                    // Schedule the morning task to run every 24 hours at 6 AM
                    PeriodicWorkRequest morningWorkRequest = new PeriodicWorkRequest.Builder(
                            MorEveWeatherUpdateWorker.class, 24, TimeUnit.HOURS)
                            .addTag(morningWorkManagerTag)
                            .setInitialDelay(morningInitialDelay, TimeUnit.MILLISECONDS)
                            .build();

                    // Schedule the evening task to run every 24 hours at 6 PM
                    PeriodicWorkRequest eveningWorkRequest = new PeriodicWorkRequest.Builder(
                            MorEveWeatherUpdateWorker.class, 24, TimeUnit.HOURS)
                            .addTag(eveningWorkManagerTag)
                            .setInitialDelay(eveningInitialDelay, TimeUnit.MILLISECONDS)
                            .build();

                    // Enqueue the work requests
                    WorkManager.getInstance(NotificationsActivity.this).enqueue(morningWorkRequest);
                    WorkManager.getInstance(NotificationsActivity.this).enqueue(eveningWorkRequest);
                } else {
                    WorkManager.getInstance(NotificationsActivity.this).cancelAllWorkByTag(morningWorkManagerTag);
                    WorkManager.getInstance(NotificationsActivity.this).cancelAllWorkByTag(eveningWorkManagerTag);
                    Intent intent = new Intent(NotificationsActivity.this, MorningEveningWeatherStatusService.class);
                    intent.putExtra("barStatus", isChecked);
                    startService(intent);
                }
            }
        });

    }
}