package com.weather.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.weather.weatherapp.MyApp;
import com.weather.weatherapp.R;
import com.weather.weatherapp.adapters.UnitsAdapter;
import com.weather.weatherapp.databinding.ActivitySettingsBinding;
import com.weather.weatherapp.databinding.UnitsSelectionDialogBinding;
import com.weather.weatherapp.listeners.UnitSelectionListener;
import com.weather.weatherapp.utils.Constants;
import com.weather.weatherapp.utils.Enums;
import com.weather.weatherapp.utils.SharedPreferenceUtils;
import com.weather.weatherapp.viewmodels.MainViewModel;
import com.weather.weatherapp.viewmodels.ModelClass;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity implements UnitSelectionListener {
    private ActivitySettingsBinding binding;

    SharedPreferenceUtils preferenceUtils;
    private MyApp app;
    ModelClass modelClass;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        modelClass = new ViewModelProvider(this).get(ModelClass.class);
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
          /*  Intent intent = new Intent("mode");
            sendBroadcast(intent);*/
            updateDisplayMode(SharedPreferenceUtils.THEME_DAY);
        });
        binding.darkMode.setOnClickListener(view -> {
            preferenceUtils.setAppTheme(SharedPreferenceUtils.THEME_NIGHT);
            /*Intent intent = new Intent("mode");
            sendBroadcast(intent);*/
            updateDisplayMode(SharedPreferenceUtils.THEME_NIGHT);
        });
        binding.autoMode.setOnClickListener(view -> {
            preferenceUtils.setAppTheme(SharedPreferenceUtils.THEME_AUTO);
            updateDisplayMode(SharedPreferenceUtils.THEME_AUTO);
        });

        updateViews();
        binding.tempView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String unit = Enums.Units.TEMPERATURE.getKey();
                showDialog(unit);
            }
        });
        binding.windView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String unit = Enums.Units.WIND.getKey();
                showDialog(unit);
            }
        });
        binding.pressureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String unit = Enums.Units.PRESSURE.getKey();
                showDialog(unit);
            }
        });
        binding.precipitationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String unit = Enums.Units.PRECIPITATION.getKey();
                showDialog(unit);
            }
        });
        binding.visibilityView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String unit = Enums.Units.VISIBILITY.getKey();
                showDialog(unit);
            }
        });
        binding.timeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String unit = Enums.Units.TIME.getKey();
                showDialog(unit);
            }
        });
        binding.dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String unit = Enums.Units.DATE.getKey();
                showDialog(unit);
            }
        });
    }

    public void showDialog(String unit) {
        Dialog dialog = new Dialog(this);
        UnitsSelectionDialogBinding unitsSelectionDialogBinding = UnitsSelectionDialogBinding.inflate(LayoutInflater.from(this));
        dialog.setContentView(unitsSelectionDialogBinding.getRoot());
        dialog.show();
        TextView textView = unitsSelectionDialogBinding.tvTitle;
        ArrayList<String> enums = new ArrayList<>();
        String title = "";
        if (unit.equalsIgnoreCase(Constants.TEMPERATURE)) {
            enums.addAll(Enums.TemperatureUnit.getALlTempUnits());
            title = unit + " units";
        } else if (unit.equalsIgnoreCase(Constants.WIND)) {
            enums.addAll(Enums.WindUnit.getALlWindUnits());
            title = unit + " units";
        } else if (unit.equalsIgnoreCase(Constants.PRESSURE)) {
            enums.addAll(Enums.PressureUnit.getALlPressureUnits());
            title = unit + " units";
        } else if (unit.equalsIgnoreCase(Constants.PRECIPITATION)) {
            enums.addAll(Enums.PrecipitationUnit.getALlPrecipitationUnits());
            title = unit + " units";
        } else if (unit.equalsIgnoreCase(Constants.VISIBILITY)) {
            enums.addAll(Enums.VisibilityUnit.getAllVisibilityUnits());
            title = unit + " units";
        } else if (unit.equalsIgnoreCase(Constants.TIME)) {
            enums.addAll(Enums.TimeFormat.getAllTimeFormats());
            title = unit + " formats";
        } else {
            enums.addAll(Enums.DateFormat.getAllDateFormats());
            title = unit + " formats";
        }
        textView.setText(title);
        RecyclerView recyclerView = unitsSelectionDialogBinding.unitesRecycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UnitsAdapter unitsAdapter = new UnitsAdapter(this, enums, unit, this);
        recyclerView.setAdapter(unitsAdapter);
    }

    private void updateViews() {
        SharedPreferenceUtils preferenceUtils = SharedPreferenceUtils.getInstance(this);
        binding.tvTemp.setText(preferenceUtils.getSelectedUnit(Constants.TEMPERATURE));
        binding.tvWind.setText(preferenceUtils.getSelectedUnit(Constants.WIND));
        binding.tvPressure.setText(preferenceUtils.getSelectedUnit(Constants.PRESSURE));
        binding.tvPrecipitation.setText(preferenceUtils.getSelectedUnit(Constants.PRECIPITATION));
        binding.tvVisibility.setText(preferenceUtils.getSelectedUnit(Constants.VISIBILITY));
        binding.tvTime.setText(preferenceUtils.getSelectedUnit(Constants.TIME));
        binding.tvDate.setText(preferenceUtils.getSelectedUnit(Constants.DATE));
    }

    private void updateDisplayMode(String mode) {
        app.updateTheme(mode);
        binding.lightMode.setBackground(mode.equalsIgnoreCase(SharedPreferenceUtils.THEME_DAY) ? getDrawable(R.drawable.bg_light_mode_view) : null);
        binding.darkMode.setBackground(mode.equalsIgnoreCase(SharedPreferenceUtils.THEME_NIGHT) ? getDrawable(R.drawable.bg_dark_mode_view) : null);
        binding.autoMode.setBackground(mode.equalsIgnoreCase(SharedPreferenceUtils.THEME_AUTO) ? getDrawable(R.drawable.bg_auto_mode_view) : null);
    }

    @Override
    public void onSelect(String selectedUnit, String unit) {
        SharedPreferenceUtils.getInstance(this).setSelectedUnit(selectedUnit, unit);
        updateViews();
    }
}