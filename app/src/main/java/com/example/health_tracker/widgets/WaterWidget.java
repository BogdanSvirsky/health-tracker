package com.example.health_tracker.widgets;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.health_tracker.SharedPreferencesManager;
import com.example.health_tracker.databinding.WaterWidgetBinding;

public class WaterWidget extends CardView {
    private WaterWidgetBinding binding;
    private float goal = 2200, currentMLs = 0;
    private enum Cups {SmallCup, MediumCup, LargeCup}
    private Cups currentCup = Cups.MediumCup;
    private SharedPreferencesManager sharedPreferencesManager;

    public WaterWidget(
            @NonNull Context context, SharedPreferencesManager sharedPreferencesManager
    ) {
        super(context);
        binding = WaterWidgetBinding.inflate(LayoutInflater.from(context), this);
        this.sharedPreferencesManager = sharedPreferencesManager;

        binding.addCup.setOnClickListener(v -> {
            switch (currentCup) {
                case MediumCup:
                    currentMLs += 200;
                    sharedPreferencesManager.saveMLsCount(200);
                    update();
                    break;
            }
        });

        binding.btnSettings.setOnClickListener(v -> {
            sharedPreferencesManager.reset();
            update();
        });
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public void update() {
        currentMLs = sharedPreferencesManager.getMLsCount();
        binding.txtCountMLs.setText((int) currentMLs + "");
        binding.txtPercents.setText(String.format("%.1f", (currentMLs / goal) * 100) + "%");
        binding.progressBar.setProgress((int) ((currentMLs / goal) * 100));
    }
}
