package com.weather.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.CompoundButton;

import com.weather.weatherapp.databinding.ActivityNotificationsBinding;
import com.weather.weatherapp.services.NotificationBarService;
import com.weather.weatherapp.utils.SharedPreferenceUtils;

public class NotificationsActivity extends AppCompatActivity {
    private ActivityNotificationsBinding binding;

    private SharedPreferenceUtils sharedPreferenceUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        sharedPreferenceUtils = SharedPreferenceUtils.getInstance(this);

        binding.btnBack.setOnClickListener(view -> {
            finish();
        });

        binding.notificationBarSwitch.setChecked(sharedPreferenceUtils.isNotificationBarActive());
        binding.notificationBarSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                binding.notificationBarSwitch.setChecked(isChecked);
                sharedPreferenceUtils.setNotificationBarStatus(isChecked);
//                if (isChecked) {
                    Intent intent = new Intent(NotificationsActivity.this, NotificationBarService.class);
                    intent.putExtra("barStatus",isChecked);
                    startService(intent);
                /*}else {
                    NotificationBarService barService = new NotificationBarService();
                    barService.dismissNotification();
                }*/
            }
        });

    }
}