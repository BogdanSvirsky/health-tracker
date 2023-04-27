package com.example.health_tracker.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.health_tracker.databinding.RecordsItemBinding;

public class RecordsItem extends View {
    private RecordsItemBinding binding;

    public RecordsItem(@NonNull Context context, ViewGroup container, OnClickListener onClickListener, int value) {
        super(context);
        binding = RecordsItemBinding.inflate(
                LayoutInflater.from(context),
                container,
                true
        );
        binding.getRoot().setOnClickListener(onClickListener);
        binding.txtCount.setText(value + "");
    }
}
