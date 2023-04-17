package com.example.health_tracker;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.IBinder;

import com.example.health_tracker.singletones.SharedPreferencesModule;

public class StepsCounterService extends Service implements SensorEventListener {
    private int currentStepsCount;
    private final SharedPreferencesManager sharedPreferencesManager =
            SharedPreferencesModule.getSharedPreferencesManager();

    public StepsCounterService() {
    }

    @Override
    public void onDestroy() {
        sharedPreferencesManager.saveStepsCount(currentStepsCount);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        currentStepsCount = sharedPreferencesManager.getStepsCount();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        currentStepsCount += sensorEvent.values.length;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    public int getCurrentStepsCount() {
        return currentStepsCount;
    }
}