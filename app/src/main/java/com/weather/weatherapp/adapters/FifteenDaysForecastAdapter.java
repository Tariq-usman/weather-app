package com.weather.weatherapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weatherapp.databinding.RowItemFifteenDayBinding;

public class FifteenDaysForecastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowItemFifteenDayBinding binding = RowItemFifteenDayBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FifteenDaysViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class FifteenDaysViewHolder extends RecyclerView.ViewHolder {
        private RowItemFifteenDayBinding binding;

        public FifteenDaysViewHolder(@NonNull RowItemFifteenDayBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
