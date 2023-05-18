package com.example.health_tracker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.health_tracker.singletones.SharedPreferencesModule;

public class SaveService extends Service {
    private final SharedPreferencesManager sharedPreferencesManager =
            SharedPreferencesModule.getSharedPreferencesManager();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO: there will be a post-request to a server
        sharedPreferencesManager.reset();
        stopSelf();
        return START_NOT_STICKY ;
    }
}