package com.example.health_tracker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.health_tracker.databinding.ActivityMainBinding;
import com.example.health_tracker.singletones.SharedPreferencesModule;
import com.example.health_tracker.widgets.StepsWidget;
import com.example.health_tracker.widgets.WaterWidget;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private StepsWidget stepsWidget;
    private WaterWidget waterWidget;
    private final SharedPreferencesManager sharedPreferencesManager = SharedPreferencesModule.getSharedPreferencesManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (!sharedPreferencesManager.getSensorStatus()) {
            startService(new Intent(this, StepsCounterService.class));
            sharedPreferencesManager.setSensorStatus(true);
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(
                    new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 101
            );
        }

        stepsWidget = new StepsWidget(this);
        waterWidget = new WaterWidget(this);
        binding.rootContainer.addView(stepsWidget);
        binding.rootContainer.addView(waterWidget);

        sharedPreferencesManager.reset(); // debug
    }

    @Override
    protected void onResume() {
        super.onResume();

        stepsWidget.update();
        waterWidget.update();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, StepsCounterService.class)); // debug
        sharedPreferencesManager.setSensorStatus(false);
    }
}