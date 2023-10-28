package com.weather.weatherapp.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weatherapp.R;
import com.weather.weatherapp.adapters.UnitsAdapter;
import com.weather.weatherapp.databinding.UnitsDialogFragmentBinding;
import com.weather.weatherapp.databinding.UnitsSelectionDialogBinding;
import com.weather.weatherapp.utils.Enums;

import java.util.ArrayList;

public class UnitsDialogFragment extends DialogFragment {
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
                showDialog();
            }
        });
    }

    public void showDialog() {
        Dialog dialog = new Dialog(requireContext());
        UnitsSelectionDialogBinding unitsSelectionDialogBinding = UnitsSelectionDialogBinding.inflate(LayoutInflater.from(requireContext()));
        dialog.setContentView(unitsSelectionDialogBinding.getRoot());
        dialog.show();
        ArrayList<String> enums = new ArrayList<>();
        enums.addAll(Enums.VisibilityUnit.getAllVisibilityUnits());
        RecyclerView recyclerView = unitsSelectionDialogBinding.unitesRecycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        UnitsAdapter unitsAdapter = new UnitsAdapter(requireContext(),enums);
        recyclerView.setAdapter(unitsAdapter);

    }
}
