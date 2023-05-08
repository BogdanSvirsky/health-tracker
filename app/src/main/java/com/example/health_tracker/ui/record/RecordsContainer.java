package com.example.health_tracker.ui.record;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class RecordsContainer {
    private ArrayList<RecordsItem> records = new ArrayList<>();
    private AlertDialog.Builder builder;
    private LastRecordsView lastRecordsView;
    private MaterialButton btnAddRecord;
    private Context context;
    private OnRecordsChangeListener changeListener;

    public RecordsContainer(Context context) {
        lastRecordsView = new LastRecordsView(context);
        btnAddRecord = new MaterialButton(context);
        btnAddRecord.setText("Добавить запись +");
        btnAddRecord.setOnClickListener(v -> addRecord());
        lastRecordsView.setVisibility(View.GONE);
        this.context = context;
    }

    private void addRecord() {
        builder = new AlertDialog.Builder(context);
        NumberPicker numberPicker = new NumberPicker(context);
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
                    addNewRecord(value);
                }
            }
        });
        builder.show();
    }

    public void addNewRecord(int value) {
        RecordsItem newRecord = new RecordsItem(context);
        newRecord.setValue(value);
        newRecord.setOnClickListener(view -> {
            lastRecordsView.removeRecord(newRecord);
            records.remove(newRecord);
            if (records.isEmpty())
                lastRecordsView.setVisibility(View.GONE);
            changeListener.onChange(RecordsContainer.this);
        });
        lastRecordsView.addRecord(newRecord);
        records.add(newRecord);
        lastRecordsView.setVisibility(View.VISIBLE);
        changeListener.onChange(RecordsContainer.this);
    }

    public LastRecordsView getLastRecordsView() {
        return lastRecordsView;
    }

    public Button getBtnAddRecord() {
        return btnAddRecord;
    }

    public int getRecordsSum() {
        int result = 0;
        for (RecordsItem record : records)
            result += record.getValue();
        return result;
    }

    public void setOnRecordsChangeListener(OnRecordsChangeListener changeListener){
        this.changeListener = changeListener;
    }

    public void clear() {
        records.clear();
    }
}
