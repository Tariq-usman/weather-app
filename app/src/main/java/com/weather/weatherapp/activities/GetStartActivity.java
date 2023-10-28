package com.weather.weatherapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;

import com.weather.weatherapp.databinding.ActivityGetStartBinding;
import com.weather.weatherapp.databinding.AllowLocationDialogBinding;
import com.weather.weatherapp.utils.Constants;
import com.weather.weatherapp.utils.SharedPreferenceUtils;
import com.weather.weatherapp.utils.Utils;

public class GetStartActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        Intent alternativeIntent;
        if (SharedPreferenceUtils.getInstance(getApplicationContext()).isNotFirst()) {
            alternativeIntent = new Intent(this, MainActivity.class);
            startActivity(alternativeIntent);
            finish();
        }
    }

    private Dialog dialog;
    private ActivityGetStartBinding binding;
    private SharedPreferenceUtils preferenceUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGetStartBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        Utils.hideStatusBar(this);
        preferenceUtils = SharedPreferenceUtils.getInstance(this);
        binding.btnGetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(GetStartActivity.this);
                AllowLocationDialogBinding locationDialogBinding = AllowLocationDialogBinding.inflate(LayoutInflater.from(GetStartActivity.this));
                dialog.setContentView(locationDialogBinding.getRoot());
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                locationDialogBinding.btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent, Constants.LOCATION);
                    }
                });
            }
        });
        String setUpManual = "<font><u>Set up manually</u></font>";
        binding.setUpManually.setText(Utils.formatHtml(setUpManual));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.LOCATION/* && requestCode== Activity.RESULT_OK*/) {
            dialog.dismiss();
            SharedPreferenceUtils.getInstance(this).setIsNotFirst(true);
        }/*else {
            Toast.makeText(this, "Please enable location", Toast.LENGTH_SHORT).show();
        }*/
    }
}