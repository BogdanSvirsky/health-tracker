package com.example.health_tracker.ui.widgets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;

import com.example.health_tracker.ui.record.RecordsContainer;
import com.example.health_tracker.ui.record.RecordsItem;
import com.example.health_tracker.SharedPreferencesManager;
import com.example.health_tracker.databinding.CaloriesWidgetBottomBinding;
import com.example.health_tracker.databinding.CaloriesWidgetStartBinding;
import com.example.health_tracker.singletones.SharedPreferencesModule;

import java.util.ArrayList;

public class CaloriesWidget extends BaseWidget {
    private int caloriesCount = 0;
    private float goal = 1700f;
    private AlertDialog.Builder builder;
    private CaloriesWidgetStartBinding startBinding;
    private CaloriesWidgetBottomBinding bottomBinding;
    private RecordsContainer caloriesRecords;
    private SharedPreferencesManager sharedPreferencesManager =
            SharedPreferencesModule.getSharedPreferencesManager();

    public CaloriesWidget(@NonNull Context context) {
        super(context);
        startBinding = CaloriesWidgetStartBinding.inflate(
                LayoutInflater.from(context),
                baseBinding.startContent,
                true
        );
        bottomBinding = CaloriesWidgetBottomBinding.inflate(
                LayoutInflater.from(context),
                baseBinding.bottomContent,
                true
        );

        caloriesRecords = new RecordsContainer(context);

        bottomBinding.content.addView(caloriesRecords.getLastRecordsView());
        bottomBinding.content.addView(caloriesRecords.getBtnAddRecord());
        caloriesRecords.setOnRecordsChangeListener(c -> update());

        update();
    }

    public void update() {
        caloriesCount = caloriesRecords.getRecordsSum();
        startBinding.txtCountCalories.setText(caloriesCount + "");
        baseBinding.txtPercents.setText(String.format("%.1f", (caloriesCount / goal) * 100) + "%");
        baseBinding.progressBar.setProgress((int) ((caloriesCount / goal) * 100));
    }

    public void reset() {
        // TODO: create a streak of successful days
        caloriesRecords.clear();
    }
}
