package com.example.health_tracker.ui.record;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

import com.example.health_tracker.SharedPreferencesManager;
import com.example.health_tracker.singletones.SharedPreferencesModule;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class RecordsContainer {
    private final SharedPreferencesManager sharedPreferencesManager =
            SharedPreferencesModule.getSharedPreferencesManager();
    private final ArrayList<RecordsItem> records = new ArrayList<>();
    private final LastRecordsView lastRecordsView;
    private final MaterialButton btnAddRecord;
    private final Context context;
    private OnRecordsChangeListener changeListener;
    private final SharedPreferencesManager.KEYS KEY;

    public RecordsContainer(Context context, SharedPreferencesManager.KEYS enumKey) {
        lastRecordsView = new LastRecordsView(context);
        btnAddRecord = new MaterialButton(context);
        btnAddRecord.setText("Добавить запись +");
        btnAddRecord.setOnClickListener(v -> startRecordDialog());
        lastRecordsView.setVisibility(View.GONE);
        this.context = context;
        KEY = enumKey;
    }

    private void startRecordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        NumberPicker numberPicker = new NumberPicker(context);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(2000);
        builder.setView(numberPicker);
        builder.setTitle("Введите количество");
        builder.setCancelable(true);
        builder.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int value = numberPicker.getValue();
                if (value != 0) {
                    addNewRecord(value);
                }
            }
        });
        builder.show();
    }

    public void addNewRecord(int value) {
        addRecord(value);
        changeListener.onChange(RecordsContainer.this);
    }

    private void addRecord(final int value) {
        RecordsItem newRecord = new RecordsItem(context);
        newRecord.setValue(value);
        newRecord.setOnClickListener(view -> {
            lastRecordsView.removeRecord(newRecord);
            records.remove(newRecord);
            Log.d("RECORDS CONTAINER", String.valueOf(records.size()));
            if (records.isEmpty())
                lastRecordsView.setVisibility(View.GONE);
            changeListener.onChange(RecordsContainer.this);
        });
        lastRecordsView.addRecord(newRecord);
        records.add(newRecord);
        lastRecordsView.setVisibility(View.VISIBLE);
    }

    public LastRecordsView getLastRecordsView() {
        return lastRecordsView;
    }

    public MaterialButton getBtnAddRecord() {
        return btnAddRecord;
    }

    public int getRecordsSum() {
        int result = 0;
        for (RecordsItem record : records)
            result += record.getValue();
        return result;
    }

    public void setOnRecordsChangeListener(OnRecordsChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    public void save() {
        StringBuilder saveString = new StringBuilder();
        for (RecordsItem record : records)
            saveString.append(record.getValue()).append(" ");
        sharedPreferencesManager.saveRecords(KEY, saveString.toString());
    }

    public void update() {
        String saveString = sharedPreferencesManager.getRecords(KEY);
        records.clear();
        lastRecordsView.clear();
        if (saveString != "") {
            for (String value : saveString.split(" ")) {
                addRecord(Integer.parseInt(value));
            }
        }
    }
}
