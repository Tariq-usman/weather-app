package com.weather.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.weather.weatherapp.R;
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
        int colorNight = ContextCompat.getColor(this, R.color.trans);

        ObjectAnimator colorAnimation = ObjectAnimator.ofArgb(binding.nightBg, "backgroundColor", colorNight);
        colorAnimation.setDuration(1000); // Set the duration of the color transition in milliseconds (1 second in this example)
        boolean isNightMode = false; // Determine whether it's night mode
        if (isNightMode) {
            colorAnimation.start(); // Start the animation for night mode
        }
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