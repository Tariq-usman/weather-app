package com.weather.weatherapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weatherapp.activities.TwentyFourHrsForecastActivity;
import com.weather.weatherapp.databinding.RowItemDailyForecastBinding;
import com.weather.weatherapp.fragments.HomeFragment;


public class DailyForecastAdapter extends RecyclerView.Adapter<DailyForecastAdapter.DailyForecastViewHolder> {

    public int lastPosition = 0;
    private Context context;

    public DailyForecastAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DailyForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RowItemDailyForecastBinding binding = RowItemDailyForecastBinding.inflate(inflater, parent, false);
        return new DailyForecastViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyForecastViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TwentyFourHrsForecastActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class DailyForecastViewHolder extends RecyclerView.ViewHolder {
        RowItemDailyForecastBinding binding;

        public DailyForecastViewHolder(RowItemDailyForecastBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
