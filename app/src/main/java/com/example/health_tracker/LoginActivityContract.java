package com.example.health_tracker;

import android.content.Context;
import android.content.Intent;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.health_tracker.ui.activities.LoginActivity;

public class LoginActivityContract extends ActivityResultContract<String, String> {
    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, @Nullable String s) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public @Nullable String parseResult(int i, @Nullable Intent intent) {
        return null;
    }
}
