package com.example.health_tracker;

import android.content.Context;
import android.content.Intent;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.health_tracker.ui.activities.WidgetsActivity;

public class WidgetsActivityContract extends ActivityResultContract<String, String> {
    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, String s) {
        return new Intent(context, WidgetsActivity.class);
    }

    @Override
    public String parseResult(int i, @Nullable Intent intent) {
        return null;
    }
}
