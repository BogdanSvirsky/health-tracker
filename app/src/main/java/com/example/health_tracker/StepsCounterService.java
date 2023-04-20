package com.example.health_tracker;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

import com.example.health_tracker.singletones.SharedPreferencesModule;

public class StepsCounterService extends Service implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor stepsDetectorSensor;
    private static int currentStepsCount;
    private final SharedPreferencesManager sharedPreferencesManager =
            SharedPreferencesModule.getSharedPreferencesManager();

    @Override
    public void onCreate() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Log.d(
                "SENSOR",
                "SENSOR MANAGER IS " + ((sensorManager == null) ? "NULL" : "NOT NULL")
        );
        stepsDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if (stepsDetectorSensor == null) {
            Log.d("SENSOR", "SENSOR IS NULL");
        } else {
            Log.d("SENSOR", "SENSOR WAKED UP");
            sensorManager.registerListener(
                    this, stepsDetectorSensor, SensorManager.SENSOR_DELAY_NORMAL
            );
        }
        super.onCreate();
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

    public static int getCurrentStepsCount() {
        return currentStepsCount;
    }
}