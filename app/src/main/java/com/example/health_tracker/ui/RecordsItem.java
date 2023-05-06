package com.example.health_tracker.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.health_tracker.databinding.RecordsItemBinding;
import com.example.health_tracker.ui.widgets.BaseWidget;
import com.example.health_tracker.ui.widgets.CaloriesWidget;

import java.util.List;

public class RecordsItem extends LinearLayout {
    private RecordsItemBinding binding;
    private int value;
    private boolean isDeleted = false;

    public RecordsItem(Context context, ViewGroup container, BaseWidget parentView, int value) {
        super(context);
        binding = RecordsItemBinding.inflate(
                LayoutInflater.from(context), container
        );
        container.addView(this);
        binding.getRoot().setOnClickListener(v -> {
            isDeleted = true;
            parentView.update();
        });
        this.value = value;
        binding.txtCount.setText(value + "");
        binding.getRoot().setVisibility(VISIBLE);
    }


    public boolean isDeleted() {
        return isDeleted;
    }

    public int getValue() {
        return value;
    }
}
