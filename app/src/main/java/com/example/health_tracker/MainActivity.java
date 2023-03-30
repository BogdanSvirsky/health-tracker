package com.example.health_tracker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.health_tracker.databinding.ActivityMainBinding;
import com.example.health_tracker.health_managers.WaterManager;
import com.example.health_tracker.widgets.StepsWidget;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor stepsSensor;
    private ActivityMainBinding binding;
    private StepsWidget stepsWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            requestPermissions(
                    new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 101
            );
        }
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Log.d(
                "SENSOR",
                "SENSOR MANAGER IS " + ((sensorManager == null)? "NULL" : "NOT NULL")
        );
        stepsSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (stepsSensor == null){
            Log.d("SENSOR", "SENSOR IS NULL");

        } else {
            Log.d("SENSOR", "SENSOR WAKED UP");
            sensorManager.registerListener(
                    stepsWidget, stepsSensor, SensorManager.SENSOR_DELAY_NORMAL
            );
        }

        stepsWidget = new StepsWidget(this);
        binding.rootContainer.addView(stepsWidget);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(
                stepsWidget, stepsSensor, SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(stepsWidget);
    }
}