package com.example.health_tracker.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.health_tracker.DataRepository;
import com.example.health_tracker.SharedPreferencesManager;
import com.example.health_tracker.Utils;
import com.example.health_tracker.databinding.ActivityLoginBinding;
import com.example.health_tracker.models.User;
import com.example.health_tracker.singletones.SharedPreferencesModule;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private final SharedPreferencesManager sharedPreferencesManager =
            SharedPreferencesModule.getSharedPreferencesManager();
    private final DataRepository dataRepository = new DataRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setResult(Utils.RESULT_LOGIN);
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

    private void login(String username, String password) {
        if (isCorrect(username) && isCorrect(password)) {
            if (!binding.chckDoctor.isChecked()) {
                User user = dataRepository.getUser(username, password);
                if (user != null) {
                    sharedPreferencesManager.setGoal(
                            SharedPreferencesManager.KEYS.STEPS, user.getStepGoal()
                    );
                    sharedPreferencesManager.setGoal(
                            SharedPreferencesManager.KEYS.WATER, user.getWaterGoal()
                    );
                    sharedPreferencesManager.setGoal(
                            SharedPreferencesManager.KEYS.CALORIES, user.getCaloriesGoal()
                    );
                    sharedPreferencesManager.setLogStatus(true);
                    sharedPreferencesManager.reset();
                } else {
                    sharedPreferencesManager.setGoal(
                            SharedPreferencesManager.KEYS.STEPS, 10000
                    );
                    sharedPreferencesManager.setGoal(
                            SharedPreferencesManager.KEYS.WATER, 2000
                    );
                    sharedPreferencesManager.setGoal(
                            SharedPreferencesManager.KEYS.CALORIES, 4000
                    );
                    sharedPreferencesManager.setLogStatus(true);
                    Toast.makeText(this, "Ошибка входа", Toast.LENGTH_SHORT)
                            .show();
                }
                finish();
            } else {
                // TODO: something happens here
            }
        }
    }

    private void register(String username, String password) {
        if (isCorrect(username) && isCorrect(password)) {
            // TODO: create a post-request with adding user and set goals
            finish();
        }
    }

    private boolean isCorrect(String string) {
        if (!Pattern.matches("[a-zA-Z0-9]+", string)) {
            Toast.makeText(this, "Имя пользователя или пароль некорректен", Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        return true;
    }

    public static Intent getInstance(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}