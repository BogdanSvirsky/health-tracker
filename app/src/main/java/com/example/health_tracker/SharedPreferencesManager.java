package com.example.health_tracker;

import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private final String MLs_KEY = "COUNT_MLs";
    private SharedPreferences sharedPreferences;

    public SharedPreferencesManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveMLsCount (int countMLs) {
        int currentCount = sharedPreferences.getInt(MLs_KEY, 0);
        sharedPreferences.edit()
                .putInt(MLs_KEY, currentCount + countMLs)
                .apply();
    }

    public int getMLsCount() {
        return sharedPreferences.getInt(MLs_KEY, 0);
    }

    public void reset() {
        sharedPreferences.edit()
                .putInt(MLs_KEY, 0)
                .apply();
    }
}
