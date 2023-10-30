package com.weather.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.weather.weatherapp.adapters.SearchLocationsAdapter;
import com.weather.weatherapp.databinding.ActivityLocationsSearchBinding;

public class LocationsSearchActivity extends AppCompatActivity {
    private ActivityLocationsSearchBinding binding;
    private RecyclerView locationsRecycler;
    private SearchLocationsAdapter locationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocationsSearchBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        locationsRecycler = binding.locationsRecycler;
        locationsRecycler.setLayoutManager(new LinearLayoutManager(this));
        locationsAdapter = new SearchLocationsAdapter();
        locationsRecycler.setAdapter(locationsAdapter);
    }
}