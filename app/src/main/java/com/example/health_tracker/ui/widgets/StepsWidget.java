package com.example.health_tracker.ui.widgets;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.example.health_tracker.StepsCounterService;
import com.example.health_tracker.databinding.StepsWidgetBinding;
import com.example.health_tracker.singletones.SensorManagerModule;

public class StepsWidget extends BaseWidget implements SensorEventListener {
    private StepsWidgetBinding binding;
    private SensorManager sensorManager = SensorManagerModule.getSensorManager();
    private Sensor stepCounterSensor;
    private int goal = 10000;
    private float countSteps = 0, startSteps = 0;
    private boolean isReset = false;

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public StepsWidget(@NonNull Context context) {
        super(context);
        binding = StepsWidgetBinding.inflate(
                LayoutInflater.from(context),
                baseBinding.startContent,
                true
        );
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (stepCounterSensor != null)
            sensorManager.registerListener(
                    this,
                    stepCounterSensor,
                    SensorManager.SENSOR_DELAY_NORMAL
            );
        Log.d(
                "STEPS WIDGET",
                "SENSOR IS " + ((stepCounterSensor == null) ? "NULL": "WORKING")
        );

        update();
    }

    public void update() {
        binding.txtCountSteps.setText(countSteps + "");
        binding.txtCountKMs.setText(String.format("это %.1f км", ((float) countSteps) / goal) + "");
        baseBinding.txtPercents.setText(String.format("%.1f", (((float) countSteps) / goal) * 100) + "%");
        baseBinding.progressBar.setProgress((int) ((countSteps / goal) * 100));
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float currentStepsCount = sensorEvent.values[0];
        if (!isReset) {
            isReset = true;
            startSteps = currentStepsCount;
        }
        else
            countSteps = currentStepsCount - startSteps;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    public void reset() {
        isReset = false;
    }

    public void unregisterListener() {
        sensorManager.unregisterListener(this);
    }
}
