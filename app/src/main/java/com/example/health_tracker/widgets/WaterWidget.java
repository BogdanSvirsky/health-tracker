package com.example.health_tracker.widgets;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.health_tracker.databinding.WaterWidgetBinding;

public class WaterWidget extends CardView {
    private WaterWidgetBinding binding;
    private int goal, currentMLs = 0;
    private enum Cups {SmallCup, MediumCup, LargeCup}
    private Cups currentCup = Cups.MediumCup;

    public WaterWidget(@NonNull Context context) {
        super(context);
        binding = WaterWidgetBinding.inflate(LayoutInflater.from(context), this);

        binding.addCup.setOnClickListener(v -> {
            switch (currentCup) {
                case MediumCup: currentMLs += 200;
            }
        });
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }
}
