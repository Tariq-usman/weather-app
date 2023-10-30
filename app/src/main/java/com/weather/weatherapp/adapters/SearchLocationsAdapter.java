package com.weather.weatherapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weatherapp.databinding.RowItemLocationsBinding;

public class SearchLocationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowItemLocationsBinding binding = RowItemLocationsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LocationsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class LocationsViewHolder extends RecyclerView.ViewHolder {
        RowItemLocationsBinding binding;

        public LocationsViewHolder(@NonNull RowItemLocationsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
