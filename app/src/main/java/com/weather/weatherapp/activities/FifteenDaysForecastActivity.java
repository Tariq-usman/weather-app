package com.weather.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.weather.weatherapp.R;
import com.weather.weatherapp.adapters.FifteenDaysForecastAdapter;
import com.weather.weatherapp.databinding.ActivityFifteenDaysForecastBinding;
import com.weather.weatherapp.utils.SharedPreferenceUtils;
import com.weather.weatherapp.utils.Utils;

public class FifteenDaysForecastActivity extends AppCompatActivity {

    private SharedPreferenceUtils preferenceUtils;
    private ActivityFifteenDaysForecastBinding binding;
    private RecyclerView fifteenDaysForecastRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFifteenDaysForecastBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        Utils.overlayStatusBar(this);
        preferenceUtils = SharedPreferenceUtils.getInstance(getApplicationContext());

        String theme = preferenceUtils.getAppTheme();
        int colorNight = ContextCompat.getColor(FifteenDaysForecastActivity.this, R.color.trans);
        ObjectAnimator colorAnimation = ObjectAnimator.ofArgb(binding.nightBg, "backgroundColor", colorNight);
        colorAnimation.setDuration(100); // Set the duration of the color transition in milliseconds (1 second in this example)
        boolean isNightMode = false; // Determine whether it's night mode
        if (theme.equalsIgnoreCase(SharedPreferenceUtils.THEME_DAY)) {
            isNightMode = false;
        } else if (theme.equalsIgnoreCase(SharedPreferenceUtils.THEME_NIGHT)) {
            isNightMode = true;

        }
        if (isNightMode) {
            colorAnimation.start(); // Start the animation for night mode
        } else {
            colorAnimation.reverse();
        }

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fifteenDaysForecastRecycler = binding.fifteenDaysForecastRecycler;
        fifteenDaysForecastRecycler.setLayoutManager(new LinearLayoutManager(this));
        fifteenDaysForecastRecycler.setAdapter(new FifteenDaysForecastAdapter());
    }
}