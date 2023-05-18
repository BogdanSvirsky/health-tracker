package com.example.health_tracker.ui.record;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.health_tracker.databinding.LastRecordsViewBinding;

public class LastRecordsView extends LinearLayout {
    private final LastRecordsViewBinding binding;

    public LastRecordsView(Context context) {
        super(context);
        binding = LastRecordsViewBinding.inflate(LayoutInflater.from(context), this);
        setOrientation(VERTICAL);
    }

    public void addRecord(RecordsItem newRecord) {
        binding.recordsContainer.addView(newRecord);
    }

    public void removeRecord(RecordsItem record) {
        binding.recordsContainer.removeView(record);
    }

    public void clear() {
        binding.recordsContainer.removeAllViews();
    }
}