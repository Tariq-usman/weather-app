package com.weather.weatherapp.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HorizontalRecyclerItemDecorator extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int space = Utils.dpToPixel(10, parent.getContext());
        // only for the last one
        if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            outRect.right = 0;
            outRect.left = space;
        } else if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = 0;
//            outRect.right = space;
        } else {
            outRect.left = space;
        }

    }
}
