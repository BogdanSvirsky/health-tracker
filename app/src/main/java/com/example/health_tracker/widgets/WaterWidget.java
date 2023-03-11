package com.example.health_tracker.widgets;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.health_tracker.databinding.WaterWidgetBinding;

public class WaterWidget extends CardView {
    private WaterWidgetBinding binding;

    public WaterWidget(@NonNull Context context) {
        super(context);
        binding = WaterWidgetBinding.inflate(LayoutInflater.from(context), this);
    }
}
