package com.example.health_tracker.widgets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.health_tracker.SharedPreferencesManager;
import com.example.health_tracker.databinding.WaterWidgetBinding;

import java.util.ArrayList;

public class WaterWidget extends CardView {
    private WaterWidgetBinding binding;
    private float goal = 2200, currentMLs = 0;
    private ArrayList<Integer> cups = new ArrayList<>();
    private int currentCup = 1;
    private SharedPreferencesManager sharedPreferencesManager;
    private boolean isVisible = false;
    private AlertDialog.Builder builder;

    public WaterWidget(
            @NonNull Context context, SharedPreferencesManager sharedPreferencesManager
    ) {
        super(context);
        binding = WaterWidgetBinding.inflate(LayoutInflater.from(context), this);
        this.sharedPreferencesManager = sharedPreferencesManager;

        cups.add(200);
        cups.add(300);
        cups.add(400);

        binding.addCup.setOnClickListener(v -> {
            currentMLs += cups.get(currentCup);
            sharedPreferencesManager.saveMLsCount(cups.get(currentCup));
            update();
        });

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                currentCup = (int) binding.radioGroup.indexOfChild(
                        findViewById(binding.radioGroup.getCheckedRadioButtonId())
                );

                Log.d("WATER WIDGET", i + "");
            }
        });

        binding.btnSettings.setOnClickListener(v -> {
            binding.scroll.setVisibility((isVisible)? VISIBLE: GONE);
            isVisible = !isVisible;
            update();
        });

        binding.scroll.setVisibility(GONE);

        binding.btnAddNewCup.setOnClickListener(v -> addNewCup());
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public void update() {
        currentMLs = sharedPreferencesManager.getMLsCount();
        binding.txtCountMLs.setText((int) currentMLs + "");
        binding.txtPercents.setText(String.format("%.1f", (currentMLs / goal) * 100) + "%");
        binding.progressBar.setProgress((int) ((currentMLs / goal) * 100));
        binding.radioGroup.removeAllViews();
        for (int cup: cups) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(cup + " ml");
            binding.radioGroup.addView(radioButton);
        }
        binding.radioGroup.check(binding.radioGroup.getChildAt(currentCup).getId());
    }

    private void addNewCup() {
        builder = new AlertDialog.Builder(getContext());
        NumberPicker numberPicker = new NumberPicker(getContext());
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(2000);
        builder.setView(numberPicker);
        builder.setTitle("Введите размер кружки");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cups.add(numberPicker.getValue());
                Log.d("WATER WIDGET", "CUPS: " + cups.toString());
                update();
            }
        });
        builder.show();
    }
}
