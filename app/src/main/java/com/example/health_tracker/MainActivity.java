package com.example.health_tracker;

import com.example.health_tracker.health_managers.WaterManager.CUP_TYPE;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.health_tracker.databinding.ActivityMainBinding;
import com.example.health_tracker.health_managers.WaterManager;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final User user = new User();
    private final WaterManager waterManager = new WaterManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        updateManagers();

        binding.addCup.setOnClickListener(v -> {
            waterManager.addCup(CUP_TYPE.MEDIUM_CUP);
            updateManagers();
        });

        binding.btnSettings.setOnClickListener(v -> {

        });
    }

    private void updateManagers() {
        binding.waterProgress.setMax(user.getGoalValue(User.GOALS.WATER));
        binding.textWaterAmount.setText("Выпито воды: " +
                waterManager.getTodayVolume() + user.getGoalValue(User.GOALS.WATER) + " ml");
    }
}