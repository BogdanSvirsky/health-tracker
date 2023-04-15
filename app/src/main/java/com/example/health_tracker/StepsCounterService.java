package com.example.health_tracker;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class StepsCounterService extends Service implements SensorEventListener {
    private int currentCountOfSteps;
    private SharedPreferencesManager sharedPrefsManager;
    private final Binder binder = new LocalBinder();

    public StepsCounterService(SharedPreferencesManager sharedPreferencesManager) {
        this.sharedPrefsManager = sharedPreferencesManager;
    }

    public class LocalBinder extends Binder {
        LocalBinder getService() {
            return LocalBinder.this;
        }
    }

    @Override
    public void onCreate() {
        currentCountOfSteps = sharedPrefsManager.getStepsCount();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        currentCountOfSteps++;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    public int getCurrentCountOfSteps() {
        return currentCountOfSteps;
    }

    public void saveStepsCount() {
        sharedPrefsManager.saveStepsCount(currentCountOfSteps);
    }

    public void update() {
        currentCountOfSteps = sharedPrefsManager.getStepsCount(); // TODO: throw null pointer exception
    }
}
