package com.example.health_tracker;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.health_tracker.databinding.ActivityMainBinding;
import com.example.health_tracker.health_managers.WaterManager;
import com.example.health_tracker.widgets.StepsWidget;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor stepsCounter;
    private ActivityMainBinding binding;
    private final WaterManager waterManager = new WaterManager();
    private final StepsWidget stepsWidget = new StepsWidget(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepsCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER, true);

        updateManagers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(
                stepsWidget, stepsCounter, SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(stepsWidget);
    }

    private void updateManagers() {}
}