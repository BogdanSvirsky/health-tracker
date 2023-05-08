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
    private final SensorManager sensorManager = SensorManagerModule.getSensorManager();
    private final Sensor stepCounterSensor;
    private float goal = 10000;
    private int currentStepsCount = 0, previousStepCount = 0;
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
        binding.txtCountSteps.setText(String.valueOf(currentStepsCount));
        binding.txtCountKMs.setText(String.format("это %.1f км",  currentStepsCount / goal) + "");
        baseBinding.txtPercents.setText(String.format("%.1f", (currentStepsCount / goal) * 100) + "%");
        baseBinding.progressBar.setProgress((int) ((currentStepsCount / goal) * 100));
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorStepsCount = (int) sensorEvent.values[0];
        if (isReset){
            if (previousStepCount == 0)
                currentStepsCount = sensorStepsCount;
            else
                currentStepsCount = sensorStepsCount - previousStepCount;
        } else {
            previousStepCount = sensorStepsCount;
            currentStepsCount = 0;
            isReset = true;
        }
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
