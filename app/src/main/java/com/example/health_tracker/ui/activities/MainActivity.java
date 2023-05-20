package com.example.health_tracker.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.health_tracker.LoginActivityContract;
import com.example.health_tracker.SharedPreferencesManager;
import com.example.health_tracker.Utils;
import com.example.health_tracker.WidgetsActivityContract;
import com.example.health_tracker.singletones.SharedPreferencesModule;

public class MainActivity extends AppCompatActivity {
    private final SharedPreferencesManager sharedPreferencesManager =
            SharedPreferencesModule.getSharedPreferencesManager();
    private ActivityResultLauncher<String> loginActivityLauncher, widgetsActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginActivityLauncher = registerForActivityResult(
                new LoginActivityContract(), new ActivityResultCallback<String>() {
                    @Override
                    public void onActivityResult(String result) {
                        widgetsActivityLauncher.launch("");
                    }
                }
        );

        widgetsActivityLauncher = registerForActivityResult(
                new WidgetsActivityContract(), new ActivityResultCallback<String>() {
                    @Override
                    public void onActivityResult(String result) {
                        loginActivityLauncher.launch("");
                    }
                }
        );

        if (sharedPreferencesManager.getLogStatus()) {
            widgetsActivityLauncher.launch("");
        } else {
            loginActivityLauncher.launch("");
        }
    }
}