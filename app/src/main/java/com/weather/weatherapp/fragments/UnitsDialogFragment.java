package com.weather.weatherapp.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weatherapp.R;
import com.weather.weatherapp.adapters.UnitsAdapter;
import com.weather.weatherapp.databinding.UnitsDialogFragmentBinding;
import com.weather.weatherapp.databinding.UnitsSelectionDialogBinding;
import com.weather.weatherapp.listeners.UnitSelectionListener;
import com.weather.weatherapp.utils.Constants;
import com.weather.weatherapp.utils.Enums;
import com.weather.weatherapp.utils.SharedPreferenceUtils;

import java.util.ArrayList;

public class UnitsDialogFragment extends DialogFragment implements UnitSelectionListener {


    @Override
    public int getTheme() {

        if (isNightMode()) {
            return R.style.Theme_Weather_DialogFragmentNight;
        } else {
            return R.style.Theme_Weather_DialogFragment_;
        }
    }

    private boolean isNightMode() {
        // Determine if the current day/night mode is night mode.
        // You can use your own logic here.
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES;
    }

    private UnitsDialogFragmentBinding binding;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        // Set your custom style
        binding = UnitsDialogFragmentBinding.inflate(LayoutInflater.from(requireContext()));
        View view = binding.getRoot();
        dialog.setContentView(view);
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

        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        updateView();
    }

    private void updateView() {
        SharedPreferenceUtils preferenceUtils = SharedPreferenceUtils.getInstance(requireContext());
        binding.tvTemp.setText(preferenceUtils.getSelectedUnit(Constants.TEMPERATURE));
        binding.tvWind.setText(preferenceUtils.getSelectedUnit(Constants.WIND));
        binding.tvPressure.setText(preferenceUtils.getSelectedUnit(Constants.PRESSURE));
        binding.tvPrecipitation.setText(preferenceUtils.getSelectedUnit(Constants.PRECIPITATION));
        binding.tvVisibility.setText(preferenceUtils.getSelectedUnit(Constants.VISIBILITY));
        binding.tvTime.setText(preferenceUtils.getSelectedUnit(Constants.TIME));
        binding.tvDate.setText(preferenceUtils.getSelectedUnit(Constants.DATE));
    }

    public void showDialog(String unit) {
        Dialog dialog = new Dialog(requireContext());
        UnitsSelectionDialogBinding unitsSelectionDialogBinding = UnitsSelectionDialogBinding.inflate(LayoutInflater.from(requireContext()));
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
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        UnitsAdapter unitsAdapter = new UnitsAdapter(requireContext(), enums, unit, this);
        recyclerView.setAdapter(unitsAdapter);
    }

    @Override
    public void onSelect(String selectedUnit, String unit) {
        SharedPreferenceUtils.getInstance(getContext()).setSelectedUnit(selectedUnit, unit);
        updateView();
    }
}
