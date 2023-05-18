package com.example.health_tracker.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.health_tracker.SharedPreferencesManager;
import com.example.health_tracker.databinding.ActivityLoginBinding;
import com.example.health_tracker.singletones.SharedPreferencesModule;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private final SharedPreferencesManager sharedPreferencesManager =
        SharedPreferencesModule.getSharedPreferencesManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnLogin.setOnClickListener(
                v -> login(
                        binding.edtUsername.getText().toString(),
                        binding.edtPassword.getText().toString()
                )
        );

        binding.btnRegistration.setOnClickListener(
                v -> register(
                        binding.edtUsername.getText().toString(),
                        binding.edtPassword.getText().toString()
                )
        );
    }

    private void login(String name, String password) {
        // TODO: create a get-request with checking password and set goals
        sharedPreferencesManager.setGoal(SharedPreferencesManager.KEYS.STEPS ,15000);
        sharedPreferencesManager.setGoal(SharedPreferencesManager.KEYS.WATER ,4000);
        sharedPreferencesManager.setGoal(SharedPreferencesManager.KEYS.CALORIES ,5000);
        sharedPreferencesManager.setLogStatus(true);
        finish();
    }

    private void register(String name, String password) {
        // TODO: create a post-request with adding user and set goals
        finish();
    }

    public static Intent getInstance(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}