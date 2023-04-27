package com.example.health_tracker.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.health_tracker.Utils;
import com.example.health_tracker.databinding.BaseWidgetBinding;

public class BaseWidget extends CardView {
    protected BaseWidgetBinding baseBinding;
    private static final int HORIZONTAL_MARGIN = Utils.dpToPx(8);
    private static final int VERTICAL_MARGIN = Utils.dpToPx(8);
    private static final int CORNER_RADIUS = Utils.dpToPx(16);
    private static final int ELEVATION = Utils.dpToPx(8);

    public BaseWidget(@NonNull Context context) {
        this(context, null);
    }

    public BaseWidget(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseWidget(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        baseBinding = BaseWidgetBinding.inflate(LayoutInflater.from(context), this);
        MarginLayoutParams params = new MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(
                HORIZONTAL_MARGIN,
                VERTICAL_MARGIN,
                HORIZONTAL_MARGIN,
                VERTICAL_MARGIN
        );
        setLayoutParams(params);
        setRadius(CORNER_RADIUS);
        setCardElevation(ELEVATION);
    }
}
