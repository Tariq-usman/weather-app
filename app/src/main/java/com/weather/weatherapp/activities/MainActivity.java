package com.weather.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.weather.weatherapp.R;
import com.weather.weatherapp.databinding.ActivityMainBinding;
import com.weather.weatherapp.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private View btnMenu;
    NavigationView navView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); //For night mode theme
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //For day mode theme

        setContentView(binding.getRoot());
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, new HomeFragment()).commit();
        btnMenu = binding.btnOpenDrawer;
        drawerLayout = binding.drawerLayout;
        navView = binding.navView;
        navView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            // Handle the item click here
            switch (itemId) {

                case R.id.iconDescription:
                    startActivity(new Intent(this,IconsDescActivity.class));
                    break;
/*
                case R.id.nav_item2:
                    // Handle item 2 click
                    break;
*/
                // Add more cases for other items
            }
            // Close the drawer after item selection
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}