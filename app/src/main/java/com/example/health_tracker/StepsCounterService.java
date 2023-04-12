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
    
    public class LocalBinder extends Binder {
        LocalBinder getService() {
            return LocalBinder.this;
        }
    }

    @Override
    public void onCreate() {
        currentCountOfSteps = 0; // TODO: create a load from shared preferences
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        currentCountOfSteps++;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
