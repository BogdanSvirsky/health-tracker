package com.example.health_tracker.ui.widgets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;

import com.example.health_tracker.SharedPreferencesManager;
import com.example.health_tracker.databinding.WaterWidgetBottomBinding;
import com.example.health_tracker.databinding.WaterWidgetStartBinding;
import com.example.health_tracker.singletones.SharedPreferencesModule;

import java.util.ArrayList;

public class WaterWidget extends BaseWidget {
    private final WaterWidgetStartBinding startBinding;
    private final WaterWidgetBottomBinding bottomBinding;
    private float goal = 2200, currentMLs = 0;
    private ArrayList<Integer> cups = new ArrayList<>();
    private int currentCup = 1;
    private final SharedPreferencesManager sharedPreferencesManager =
            SharedPreferencesModule.getSharedPreferencesManager();
    private boolean isVisible = false;
    private AlertDialog.Builder builder;

    public WaterWidget(@NonNull Context context) {
        super(context);
        startBinding = WaterWidgetStartBinding.inflate(
                LayoutInflater.from(context),
                baseBinding.startContent,
                true
        );
        bottomBinding = WaterWidgetBottomBinding.inflate(
                LayoutInflater.from(context),
                baseBinding.bottomContent,
                true
        );

        cups.add(200);
        cups.add(300);
        cups.add(400);

        startBinding.addCup.setOnClickListener(v -> {
            currentMLs += cups.get(currentCup);
            sharedPreferencesManager.saveMLsCount(cups.get(currentCup));
            update();
        });

        bottomBinding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                currentCup = (int) bottomBinding.radioGroup.indexOfChild(
                        findViewById(bottomBinding.radioGroup.getCheckedRadioButtonId())
                );

                Log.d("WATER WIDGET", i + "");
            }
        });

        startBinding.btnSettings.setOnClickListener(v -> {
            bottomBinding.scrollContent.setVisibility((isVisible)? VISIBLE: GONE);
            isVisible = !isVisible;
            update();
        });

        bottomBinding.scrollContent.setVisibility(GONE);

        bottomBinding.btnAddNewCup.setOnClickListener(v -> addNewCup());

        update();
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public void update() {
        currentMLs = sharedPreferencesManager.getMLsCount();
        startBinding.txtCountMLs.setText((int) currentMLs + "");
        baseBinding.txtPercents.setText(String.format("%.1f", (currentMLs / goal) * 100) + "%");
        baseBinding.progressBar.setProgress((int) ((currentMLs / goal) * 100));
        bottomBinding.radioGroup.removeAllViews();
        for (int cup: cups) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(cup + " ml");
            bottomBinding.radioGroup.addView(radioButton);
        }
        bottomBinding.radioGroup.check(bottomBinding.radioGroup.getChildAt(currentCup).getId());
    }

    private void addNewCup() {
        builder = new AlertDialog.Builder(getContext());
        NumberPicker numberPicker = new NumberPicker(getContext());
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(2000);
        builder.setView(numberPicker);
        builder.setTitle("Введите размер кружки");
        builder.setCancelable(true);
        builder.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int value = numberPicker.getValue();
                if (value != 0) {
                    cups.add(value);
                    Log.d("WATER WIDGET", "CUPS: " + cups.toString());
                    update();
                }
            }
        });
        builder.show();
    }
}
