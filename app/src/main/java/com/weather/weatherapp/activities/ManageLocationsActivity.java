package com.weather.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.weather.weatherapp.adapters.ManageLocationsAdapter;
import com.weather.weatherapp.databinding.ActivityManageLocationsBinding;

public class ManageLocationsActivity extends AppCompatActivity {

    private ActivityManageLocationsBinding binding;
    private RecyclerView managedLocationsRecycler;
    private ManageLocationsAdapter manageLocationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManageLocationsBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.enterNameName.setOnClickListener(view -> {
            startActivity(new Intent(this,LocationsSearchActivity.class));
        });

        managedLocationsRecycler = binding.managedLocationsRecycler;
        managedLocationsRecycler.setLayoutManager(new LinearLayoutManager(this));
        manageLocationsAdapter = new ManageLocationsAdapter(this);
        managedLocationsRecycler.setAdapter(manageLocationsAdapter);

    }
}