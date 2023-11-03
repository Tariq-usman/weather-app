package com.weather.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.weather.weatherapp.R;
import com.weather.weatherapp.databinding.ActivityMainBinding;
import com.weather.weatherapp.fragments.HomeFragment;
import com.weather.weatherapp.fragments.UnitsDialogFragment;
import com.weather.weatherapp.utils.SharedPreferenceUtils;
import com.weather.weatherapp.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private View btnMenu;
    NavigationView navView;
    DrawerLayout drawerLayout;
    private MainViewModel viewModel;
    SharedPreferenceUtils preferenceUtils;

    @Override
    protected void onStart() {
        super.onStart();
        preferenceUtils = SharedPreferenceUtils.getInstance(getApplicationContext());

    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("mode")) {
                preferenceUtils = SharedPreferenceUtils.getInstance(getApplicationContext());

                String theme = preferenceUtils.getAppTheme();
                int colorNight = ContextCompat.getColor(MainActivity.this, R.color.trans);
                int colorday = ContextCompat.getColor(MainActivity.this, R.color.white);
                ObjectAnimator colorAnimation = ObjectAnimator.ofArgb(binding.nightBg, "backgroundColor", colorNight);
                colorAnimation.setDuration(1000); // Set the duration of the color transition in milliseconds (1 second in this example)
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
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        /*IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("mode");
        registerReceiver(broadcastReceiver, intentFilter);*/
        preferenceUtils = SharedPreferenceUtils.getInstance(getApplicationContext());

        String theme = preferenceUtils.getAppTheme();
        int colorNight = ContextCompat.getColor(MainActivity.this, R.color.trans);
        int colorday = ContextCompat.getColor(MainActivity.this, R.color.white);
        ObjectAnimator colorAnimation = ObjectAnimator.ofArgb(binding.nightBg, "backgroundColor", colorNight);
        colorAnimation.setDuration(1000); // Set the duration of the color transition in milliseconds (1 second in this example)
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

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, new HomeFragment()).commit();
        btnMenu = binding.btnOpenDrawer;
        drawerLayout = binding.drawerLayout;
        navView = binding.navView;
        navView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            // Handle the item click here
            switch (itemId) {
                case R.id.manageLocation:
                    startActivity(new Intent(this, ManageLocationsActivity.class));
                    break;
                case R.id.settings:
                    startActivity(new Intent(this, SettingsActivity.class));
                    break;
                case R.id.units:
                    UnitsDialogFragment unitsDialogFragment = new UnitsDialogFragment();
                    unitsDialogFragment.show(getSupportFragmentManager(), "Units");
                    break;
                case R.id.iconDescription:
                    startActivity(new Intent(this, IconsDescActivity.class));
                    break;
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