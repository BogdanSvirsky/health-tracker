package com.example.health_tracker.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.health_tracker.R;
import com.example.health_tracker.SaveService;
import com.example.health_tracker.SharedPreferencesManager;
import com.example.health_tracker.Utils;
import com.example.health_tracker.databinding.ActivityMainBinding;
import com.example.health_tracker.databinding.ActivityWidgetsBinding;
import com.example.health_tracker.singletones.SharedPreferencesModule;
import com.example.health_tracker.ui.widgets.CaloriesWidget;
import com.example.health_tracker.ui.widgets.StepsWidget;
import com.example.health_tracker.ui.widgets.WaterWidget;

import java.util.Calendar;

public class WidgetsActivity extends AppCompatActivity {
    private ActivityWidgetsBinding binding;
    private StepsWidget stepsWidget;
    private WaterWidget waterWidget;
    private CaloriesWidget caloriesWidget;
    private final SharedPreferencesManager sharedPreferencesManager =
            SharedPreferencesModule.getSharedPreferencesManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWidgetsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setResult(Utils.RESULT_WIDGETS);
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

        binding.btnEscape.setOnClickListener(v -> {
            sharedPreferencesManager.setLogStatus(false);
            finish();
        });

        setupAlarm();
    }

    private void setupAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, SaveService.class);
        PendingIntent pendingIntent = PendingIntent.getService(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
        );

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 0);

        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        );
    }

    public static Intent getInstance(Context context) {
        return new Intent(context, WidgetsActivity.class);
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
}