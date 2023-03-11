package com.example.health_tracker.widgets;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.health_tracker.databinding.StepsWidgetBinding;

public class StepsWidget extends CardView implements SensorEventListener {
    private StepsWidgetBinding binding;

    public void setGoal(int goal) {
        this.goal = goal;
    }

    private int goal = 10000;

    public StepsWidget(@NonNull Context context) {
        super(context);
        binding = StepsWidgetBinding.inflate(LayoutInflater.from(context), this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int countSteps = (int) sensorEvent.values[0];
        binding.txtCountSteps.setText(countSteps);
        binding.txtCountKMs.setText(String.format("%1.f", countSteps / 1450));
        binding.txtPercents.setText((countSteps / goal) * 100 + "%");
        binding.waterProgressBar.setProgress((countSteps / goal) * 100);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
