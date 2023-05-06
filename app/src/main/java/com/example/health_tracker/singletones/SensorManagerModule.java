package com.example.health_tracker.singletones;

import android.content.Context;
import android.hardware.SensorManager;

public class SensorManagerModule {
    private static SensorManager sensorManager = null;

    public static void init(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    public static SensorManager getSensorManager() {
        return sensorManager;
    }
}
