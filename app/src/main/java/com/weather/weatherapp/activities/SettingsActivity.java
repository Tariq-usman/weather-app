package com.weather.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.imageview.ShapeableImageView;
import com.weather.weatherapp.MyApp;
import com.weather.weatherapp.R;
import com.weather.weatherapp.databinding.ActivitySettingsBinding;
import com.weather.weatherapp.utils.SharedPreferenceUtils;

public class SettingsActivity extends AppCompatActivity {
    private ActivitySettingsBinding binding;

    SharedPreferenceUtils preferenceUtils;
    private MyApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        app = (MyApp) getApplication();
        preferenceUtils = SharedPreferenceUtils.getInstance(this);
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.notificationsSetting.setOnClickListener(view -> {
            startActivity(new Intent(this, NotificationsActivity.class));
        });


        String mode = preferenceUtils.getAppTheme();
        updateDisplayMode(mode);

        binding.lightMode.setOnClickListener(view -> {
            preferenceUtils.setAppTheme(SharedPreferenceUtils.THEME_DAY);
            updateDisplayMode(SharedPreferenceUtils.THEME_DAY);
        });
        binding.darkMode.setOnClickListener(view -> {
            preferenceUtils.setAppTheme(SharedPreferenceUtils.THEME_NIGHT);
            updateDisplayMode(SharedPreferenceUtils.THEME_NIGHT);
        });
        binding.autoMode.setOnClickListener(view -> {
            preferenceUtils.setAppTheme(SharedPreferenceUtils.THEME_AUTO);
            updateDisplayMode(SharedPreferenceUtils.THEME_AUTO);
        });
    }

    private void updateDisplayMode(String mode) {
        app.updateTheme(mode);
        binding.lightMode.setBackground(mode.equalsIgnoreCase(SharedPreferenceUtils.THEME_DAY) ? getDrawable(R.drawable.bg_light_mode_view) : null);
        binding.darkMode.setBackground(mode.equalsIgnoreCase(SharedPreferenceUtils.THEME_NIGHT) ? getDrawable(R.drawable.bg_dark_mode_view) : null);
        binding.autoMode.setBackground(mode.equalsIgnoreCase(SharedPreferenceUtils.THEME_AUTO) ? getDrawable(R.drawable.bg_auto_mode_view) : null);
    }
}