package com.example.health_tracker;

import android.app.Application;

import com.example.health_tracker.singletones.SharedPreferencesModule;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesModule.init(this);
    }
}
