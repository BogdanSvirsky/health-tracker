package com.example.health_tracker.widgets;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.example.health_tracker.StepsCounterService;
import com.example.health_tracker.databinding.StepsWidgetBinding;

public class StepsWidget extends BaseWidget {
    private StepsWidgetBinding binding;
    private int goal = 10000;
    private StepsCounterService stepsService;

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public StepsWidget(@NonNull Context context, StepsCounterService stepsService) {
        super(context);
        binding = StepsWidgetBinding.inflate(LayoutInflater.from(context), baseBinding.startContent, true);
        this.stepsService = stepsService;

        update();
    }

    public void update() {
        int countSteps = stepsService.getCurrentStepsCount();
        binding.txtCountSteps.setText(countSteps + "");
        binding.txtCountKMs.setText(String.format("это %.1f км", ((float) countSteps) / goal) + "");
        baseBinding.txtPercents.setText(String.format("%.1f", (((float) countSteps) / goal) * 100) + "%");
        baseBinding.progressBar.setProgress((int) ((((float) countSteps) / goal) * 100));
    }
}
