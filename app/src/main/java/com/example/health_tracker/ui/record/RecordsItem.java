package com.example.health_tracker.ui.record;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.health_tracker.R;
import com.example.health_tracker.Utils;
import com.example.health_tracker.databinding.RecordsItemBinding;
import com.example.health_tracker.ui.widgets.BaseWidget;
import com.example.health_tracker.ui.widgets.CaloriesWidget;

import java.util.List;

public class RecordsItem extends LinearLayout {
    private RecordsItemBinding binding;
    private int value;;
    private static final int HORIZONTAL_MARGIN = Utils.dpToPx(4);
    private static final int VERTICAL_MARGIN = Utils.dpToPx(8);
    private static final int VERTICAL_PADDING = Utils.dpToPx(4);
    private static final int HORIZONTAL_PADDING = Utils.dpToPx(8);

    public RecordsItem(Context context) {
        super(context);
        binding = RecordsItemBinding.inflate(
                LayoutInflater.from(context),
                this
        );
        setBackgroundResource(R.drawable.item_record);
        MarginLayoutParams params = new MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(HORIZONTAL_MARGIN, VERTICAL_MARGIN, HORIZONTAL_MARGIN, VERTICAL_MARGIN);
        setLayoutParams(params);
        binding.txtCount.setPadding(
                HORIZONTAL_PADDING,
                VERTICAL_PADDING,
                2,
                VERTICAL_PADDING
        );
        binding.btnDelete.setPadding(0, VERTICAL_PADDING, HORIZONTAL_PADDING, VERTICAL_PADDING);
    }

    public void setValue(int value) {
        this.value = value;
        binding.txtCount.setText(String.valueOf(value));
    }

    public int getValue() {
        return value;
    }
}
