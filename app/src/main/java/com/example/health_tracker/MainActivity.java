package com.example.health_tracker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.health_tracker.databinding.ActivityMainBinding;
import com.example.health_tracker.singletones.SharedPreferencesModule;
import com.example.health_tracker.ui.widgets.CaloriesWidget;
import com.example.health_tracker.ui.widgets.StepsWidget;
import com.example.health_tracker.ui.widgets.WaterWidget;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private StepsWidget stepsWidget;
    private WaterWidget waterWidget;
    private CaloriesWidget caloriesWidget;
    private final SharedPreferencesManager sharedPreferencesManager =
            SharedPreferencesModule.getSharedPreferencesManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(
                    new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 101
            );
        }

        stepsWidget = new StepsWidget(this);
        waterWidget = new WaterWidget(this);
        caloriesWidget = new CaloriesWidget(this);
        binding.rootContainer.addView(stepsWidget);
        binding.rootContainer.addView(waterWidget);
        binding.rootContainer.addView(caloriesWidget);

        binding.btnDebug.setOnClickListener(v -> {
            sharedPreferencesManager.reset();
            boolean sensorStatus = sharedPreferencesManager.getSensorStatus();
            update();
            Toast.makeText(this,
                    "SENSOR " + sensorStatus,
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }

    private void update() {
        stepsWidget.update();
        waterWidget.update();
        caloriesWidget.update();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stepsWidget.unregisterListener();
    }

    private void reset(){
        stepsWidget.reset();
        waterWidget.reset();
        caloriesWidget.reset();
    }
}