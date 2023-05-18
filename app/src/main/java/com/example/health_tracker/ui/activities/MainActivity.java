package com.example.health_tracker.ui.activities;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.health_tracker.SaveService;
import com.example.health_tracker.SharedPreferencesManager;
import com.example.health_tracker.Utils;
import com.example.health_tracker.databinding.ActivityMainBinding;
import com.example.health_tracker.singletones.SharedPreferencesModule;
import com.example.health_tracker.ui.widgets.CaloriesWidget;
import com.example.health_tracker.ui.widgets.StepsWidget;
import com.example.health_tracker.ui.widgets.WaterWidget;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private StepsWidget stepsWidget;
    private WaterWidget waterWidget;
    private CaloriesWidget caloriesWidget;
    private final SharedPreferencesManager sharedPreferencesManager =
            SharedPreferencesModule.getSharedPreferencesManager();
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

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

        binding.btnEscape.setOnClickListener(view -> setupAlarm());

        startActivity(LoginActivity.getInstance(this));
    }

    private void setupAlarm() {
        //startForegroundService(new Intent(this, SaveService.class));

        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, SaveService.class);
        PendingIntent pendingIntent = PendingIntent.getService(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
        );

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + 10_000,
                30_000, pendingIntent);

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