package com.weather.weatherapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weatherapp.activities.FifteenDaysForecastActivity;
import com.weather.weatherapp.activities.WeeklyForecastActivity;
import com.weather.weatherapp.databinding.RowItemWeeklyForecastBinding;
import com.weather.weatherapp.databinding.WeeklyFooterBinding;

import java.util.ArrayList;


public class WeeklyForecastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int REGULAR_VIEW = 0;
    private int FOOTER_VIEW = 1;
    int size = 7;
    private Context context;
    private ArrayList<String> weekDay;

    public WeeklyForecastAdapter(Context context, ArrayList<String> weekDay) {
        this.context = context;
        this.weekDay = weekDay;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            RowItemWeeklyForecastBinding binding = RowItemWeeklyForecastBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

            View view = binding.getRoot();
            return new WeeklyForecastViewHolder(binding);
        } else {
            WeeklyFooterBinding binding = WeeklyFooterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            View view = binding.getRoot();
            return new WeeklyFooterViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == weekDay.size()) {
            View wfView = ((WeeklyFooterViewHolder) holder).binding.getRoot();
            wfView.setOnClickListener(view1 -> {
                Log.i("viewClicked: ", "Footer view");
                Intent intent = new Intent(context, FifteenDaysForecastActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });
        } else {
            View view = ((WeeklyForecastViewHolder) holder).binding.getRoot();
            ((WeeklyForecastViewHolder) holder).binding.tvDay.setText(weekDay.get(position));
            view.setOnClickListener(view1 -> {
                Log.i("viewClicked: ", "Regular view");
                Intent intent = new Intent(context, WeeklyForecastActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        return position == weekDay.size() ? FOOTER_VIEW : REGULAR_VIEW;
    }

    @Override
    public int getItemCount() {
        return weekDay.size() + 1;
    }

    public class WeeklyForecastViewHolder extends RecyclerView.ViewHolder {
        RowItemWeeklyForecastBinding binding;

        public WeeklyForecastViewHolder(@NonNull RowItemWeeklyForecastBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class WeeklyFooterViewHolder extends RecyclerView.ViewHolder {
        WeeklyFooterBinding binding;

        public WeeklyFooterViewHolder(@NonNull WeeklyFooterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
