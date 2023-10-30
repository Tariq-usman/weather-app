package com.weather.weatherapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weatherapp.activities.ManageLocationsActivity;
import com.weather.weatherapp.databinding.RowItemManageLocationsBinding;

public class ManageLocationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    public ManageLocationsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowItemManageLocationsBinding binding = RowItemManageLocationsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
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
        RowItemManageLocationsBinding binding;

        public LocationsViewHolder(@NonNull RowItemManageLocationsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
