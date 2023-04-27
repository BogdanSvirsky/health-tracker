package com.example.health_tracker;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferencesManager {
    private final String
            CALORIES_KEY = "COUNT_CALORIES",
            MLs_KEY = "COUNT_MLs",
            STEPS_KEY = "COUNT_STEPS",
            SENSOR_KEY = "SENSOR";
    private SharedPreferences sharedPreferences;

    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(
                "HEALTH TRACKER PREFERENCES",
                MODE_PRIVATE
        );
    }

    public void saveMLsCount(int countMLs) {
        int currentCount = sharedPreferences.getInt(MLs_KEY, 0);
        sharedPreferences.edit()
                .putInt(MLs_KEY, currentCount + countMLs)
                .apply();
    }

    public int getMLsCount() {
        return sharedPreferences.getInt(MLs_KEY, 0);
    }

    public void saveStepsCount(int countSteps) {
        sharedPreferences.edit()
                .putInt(STEPS_KEY, countSteps)
                .apply();
    }

    public int getStepsCount() {
        return sharedPreferences.getInt(STEPS_KEY, 0);
    }

    public void reset() {
        sharedPreferences.edit()
                .putInt(MLs_KEY, 0)
                .apply();
        sharedPreferences.edit()
                .putInt(STEPS_KEY, 0)
                .apply();
        sharedPreferences.edit()
                .putInt(CALORIES_KEY, 0)
                .apply();
    }

    public void setSensorStatus(boolean isRunning) {
        sharedPreferences.edit()
                .putBoolean(SENSOR_KEY, isRunning)
                .apply();
        Log.d("SENSOR", (isRunning) ? "WORK" : "STOPPED");
    }

    public boolean getSensorStatus() {
        return sharedPreferences.getBoolean(SENSOR_KEY, false);
    }

    public void saveCaloriesCount(int caloriesCount) {
        sharedPreferences.edit()
                .putInt(CALORIES_KEY, caloriesCount)
                .apply();
    }

    public int getCaloriesCount() {
        return sharedPreferences.getInt(CALORIES_KEY, 0);
    }
}
