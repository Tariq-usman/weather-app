package com.weather.weatherapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weatherapp.databinding.RowItem24HoursBinding;

public class TwentyHrsForecastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowItem24HoursBinding binding = RowItem24HoursBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ForecastViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 24;
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {
        RowItem24HoursBinding binding;

        public ForecastViewHolder(@NonNull RowItem24HoursBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
