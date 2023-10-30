package com.weather.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.weather.weatherapp.databinding.ActivityNotificationsBinding;

public class NotificationsActivity extends AppCompatActivity {
    private ActivityNotificationsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        binding.btnBack.setOnClickListener(view -> {
            finish();
        });
    }
}