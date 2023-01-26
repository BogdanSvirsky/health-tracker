package com.example.health_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.health_tracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = new User();
        binding.addCup.setOnClickListener(v -> {

        });
    }
}