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

public class RecordsItem extends View {
    private RecordsItemBinding binding;
    private int value;

    public RecordsItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public RecordsItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RecordsItem(
            @NonNull Context context,
            ViewGroup container,
            OnClickListener onClickListener,
            int value
    ) {
        super(context, null);
        binding = RecordsItemBinding.inflate(LayoutInflater.from(context), container); // TODO: view isn't visible
        binding.getRoot().setOnClickListener(onClickListener);
        this.value = value;
        binding.txtCount.setText(value + "");
    }

    public int getValue() {
        return value;
    }
}
