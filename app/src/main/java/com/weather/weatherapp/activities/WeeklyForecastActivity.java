package com.weather.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.weather.weatherapp.adapters.DaysAdapter;
import com.weather.weatherapp.databinding.ActivityWeeklyForecastBinding;
import com.weather.weatherapp.utils.HorizontalRecyclerItemDecorator;

public class WeeklyForecastActivity extends AppCompatActivity {
    private ActivityWeeklyForecastBinding binding;
    private RecyclerView daysRecycler;
    private DaysAdapter daysAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeeklyForecastBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        daysRecycler=binding.daysRecycler;
        daysRecycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        daysAdapter = new DaysAdapter(this);
        daysRecycler.addItemDecoration(new HorizontalRecyclerItemDecorator());
        daysRecycler.setAdapter(daysAdapter);
    }
}