package com.example.health_tracker.health_managers;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class StepManager implements SensorEventListener {
    private int todayStepAmount = 0;
    private final Sensor stepCounterSensor;

    public void setTodayStepAmount(int todayStepAmount) {
        this.todayStepAmount = todayStepAmount;
    }

    public int getTodayStepAmount() {
        return todayStepAmount;
    }

    public StepManager(SensorManager sensorManager) {
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        todayStepAmount += sensorEvent.values[0];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    public Sensor getStepCounterSensor() {
        return stepCounterSensor;
    }
}
