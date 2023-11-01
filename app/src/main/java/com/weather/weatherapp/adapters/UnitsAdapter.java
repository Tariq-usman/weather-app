package com.weather.weatherapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weatherapp.databinding.RowItemUnitsBinding;

import java.util.ArrayList;

public class UnitsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<String> enums;
    private int lastPosition = -1;

    public UnitsAdapter(Context context, ArrayList<String> enums) {
        this.context = context;
        this.enums = enums;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowItemUnitsBinding binding = RowItemUnitsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UnitsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((UnitsViewHolder) holder).binding.tvUnit.setText(enums.get(position));
        ((UnitsViewHolder) holder).binding.radioButton.setChecked(lastPosition == position);
    }

    @Override
    public int getItemCount() {
        return enums.size();
    }

    public class UnitsViewHolder extends RecyclerView.ViewHolder {
        private RowItemUnitsBinding binding;
        TextView textView;

        public UnitsViewHolder(@NonNull RowItemUnitsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
            binding.radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }
}
