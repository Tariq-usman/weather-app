package com.weather.weatherapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weatherapp.adapters.DailyForecastAdapter;
import com.weather.weatherapp.adapters.WeeklyForecastAdapter;
import com.weather.weatherapp.databinding.FragmentHomeBinding;
import com.weather.weatherapp.utils.HorizontalRecyclerItemDecorator;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private DailyForecastAdapter dailyForecastAdapter;
    private WeeklyForecastAdapter weeklyForecastAdapter;
    private RecyclerView dailyForecastRecycler, weeklyForecastRecycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(requireContext()));
        View view = binding.getRoot();

        String degreeSymbol = "\u00B0";
        int temperature = 12;
        String temperatureText = temperature + degreeSymbol;
        binding.tvTemp.setText(temperatureText);

        dailyForecastRecycler = binding.dailyForecastRecycler;
        dailyForecastRecycler.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false));
        dailyForecastRecycler.addItemDecoration(new HorizontalRecyclerItemDecorator());
        dailyForecastAdapter = new DailyForecastAdapter(requireContext());
        dailyForecastRecycler.setAdapter(dailyForecastAdapter);

        weeklyForecastRecycler = binding.weeklyForecastRecycler;
        weeklyForecastRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        weeklyForecastAdapter = new WeeklyForecastAdapter(requireContext());
        weeklyForecastRecycler.setAdapter(weeklyForecastAdapter);

        return view;
    }
}
