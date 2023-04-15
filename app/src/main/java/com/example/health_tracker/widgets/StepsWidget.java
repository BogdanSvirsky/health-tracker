package com.example.health_tracker.widgets;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.health_tracker.StepsCounterService;
import com.example.health_tracker.databinding.StepsWidgetBinding;

public class StepsWidget extends CardView {
    private StepsWidgetBinding binding;
    private int goal = 10000;
    private StepsCounterService stepsService;

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public StepsWidget(@NonNull Context context, StepsCounterService stepsService) {
        super(context);
        binding = StepsWidgetBinding.inflate(LayoutInflater.from(context), this);
        this.stepsService = stepsService;

        update();
    }

    public void update() {
        int countSteps = stepsService.getCurrentCountOfSteps();
        binding.txtCountSteps.setText(countSteps + "");
        binding.txtCountKMs.setText(String.format("это %.1f км", ((float) countSteps) / goal) + "");
        binding.txtPercents.setText(String.format("%.1f", (((float) countSteps) / goal) * 100) + "%");
        binding.progressBar.setProgress((int) ((((float) countSteps) / goal) * 100));
    }
}
