package com.weather.weatherapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weatherapp.R;
import com.weather.weatherapp.activities.WeeklyForecastActivity;
import com.weather.weatherapp.databinding.RowItemDaysBinding;

public class DaysAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    int lastPosition = 0;

    public DaysAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowItemDaysBinding binding = RowItemDaysBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DaysViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setBackground(position == lastPosition ? context.getDrawable(R.drawable.circular_white_border) : null);
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class DaysViewHolder extends RecyclerView.ViewHolder {
        RowItemDaysBinding binding;

        public DaysViewHolder(@NonNull RowItemDaysBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }
}
