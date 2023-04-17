package com.example.health_tracker.singletones;

import android.content.Context;

import com.example.health_tracker.SharedPreferencesManager;

public class SharedPreferencesModule {
    private static SharedPreferencesManager sharedPreferencesManager = null;

    public static void init(Context context) {
        sharedPreferencesManager = new SharedPreferencesManager(context);
    }

    public static SharedPreferencesManager getSharedPreferencesManager() {
        return sharedPreferencesManager;
    }
}
