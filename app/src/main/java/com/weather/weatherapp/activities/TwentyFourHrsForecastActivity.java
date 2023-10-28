package com.weather.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.weather.weatherapp.adapters.TwentyHrsForecastAdapter;
import com.weather.weatherapp.databinding.ActivityTwentyFourForecastBinding;

public class TwentyFourHrsForecastActivity extends AppCompatActivity {
    private ActivityTwentyFourForecastBinding binding;
    private TwentyHrsForecastAdapter twentyHrsForecastAdapter;
    private RecyclerView forecastRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTwentyFourForecastBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        forecastRecycler = binding.oneDayForecastRecycler;
        forecastRecycler.setLayoutManager(new LinearLayoutManager(this));
        forecastRecycler.setAdapter(new TwentyHrsForecastAdapter());
    }
}