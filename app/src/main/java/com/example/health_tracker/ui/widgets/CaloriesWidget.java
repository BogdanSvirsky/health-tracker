package com.example.health_tracker.ui.widgets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;

import com.example.health_tracker.ui.RecordsItem;
import com.example.health_tracker.SharedPreferencesManager;
import com.example.health_tracker.databinding.CaloriesWidgetBottomBinding;
import com.example.health_tracker.databinding.CaloriesWidgetStartBinding;
import com.example.health_tracker.singletones.SharedPreferencesModule;

import java.util.ArrayList;

public class CaloriesWidget extends BaseWidget {
    private int caloriesCount = 0;
    private float goal = 1700f;
    private ArrayList<RecordsItem> caloriesRecords = new ArrayList<>();
    private AlertDialog.Builder builder;
    private CaloriesWidgetStartBinding startBinding;
    private CaloriesWidgetBottomBinding bottomBinding;
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
        startBinding.addCalories.setOnClickListener(v -> addCalories());

        update();
    }

    public void update() {
        caloriesCount = 0;
        bottomBinding.recordsContainer.removeAllViews();
        for (RecordsItem record: caloriesRecords) {
            if (record.isDeleted()) {
                caloriesRecords.remove(record);
            }
            else {
                bottomBinding.recordsContainer.addView(record);
                Log.d("CALORIES WIDGET", record.getValue() + "");
                caloriesCount += record.getValue();
            }
        }
        startBinding.txtCountCalories.setText(caloriesCount + "");
        baseBinding.txtPercents.setText(String.format("%.1f", (caloriesCount / goal) * 100) + "%");
        baseBinding.progressBar.setProgress((int) ((caloriesCount / goal) * 100));
        if (caloriesRecords.isEmpty())
            bottomBinding.txtLastRecords.setVisibility(GONE);
        else
            bottomBinding.txtLastRecords.setVisibility(VISIBLE);

    }

    private void addCalories() {
        CaloriesWidget parentView = this;
        builder = new AlertDialog.Builder(getContext());
        NumberPicker numberPicker = new NumberPicker(getContext());
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(2000);
        builder.setView(numberPicker);
        builder.setTitle("Введите количество калорий");
        builder.setCancelable(true);
        builder.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int value = numberPicker.getValue();
                if (value != 0) {
                    caloriesRecords.add(
                            new RecordsItem(
                                    getContext(),
                                    bottomBinding.recordsContainer,
                                    parentView,
                                    value
                            )
                    );
                    update();
                }
            }
        });
        builder.show();
    }
}
