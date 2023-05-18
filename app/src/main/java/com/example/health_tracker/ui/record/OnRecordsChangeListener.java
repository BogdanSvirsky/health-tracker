package com.example.health_tracker.ui.record;

import androidx.annotation.CallSuper;

public class OnRecordsChangeListener {
    @CallSuper
    public void onChange(RecordsContainer recordsContainer) {
        recordsContainer.save();
        recordsContainer.update();
    }
}
