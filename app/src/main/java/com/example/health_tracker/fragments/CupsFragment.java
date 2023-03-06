package com.example.health_tracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.health_tracker.databinding.CupsFragmentBinding;
import com.example.health_tracker.health_managers.WaterManager;

public class CupsFragment extends Fragment {
    private CupsFragmentBinding binding;

    private WaterManager waterManager;

    public CupsFragment(WaterManager waterManager) {
        this.waterManager = waterManager;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = CupsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.cupsSelect.setOnClickListener(v -> {
            if (binding.btn200ml.isChecked()) waterManager
                    .setCurrentCup(WaterManager.CUP_TYPE.SMALL_CUP);
            else if (binding.btn300ml.isChecked()) waterManager
                    .setCurrentCup(WaterManager.CUP_TYPE.SMALL_MEDIUM_CUP);
            else if (binding.btn400ml.isChecked()) waterManager
                    .setCurrentCup(WaterManager.CUP_TYPE.MEDIUM_CUP);
            else if (binding.btn500ml.isChecked()) waterManager
                    .setCurrentCup(WaterManager.CUP_TYPE.BIG_MEDIUM_CUP);
            else if (binding.btn600ml.isChecked()) waterManager
                    .setCurrentCup(WaterManager.CUP_TYPE.BIG_CUP);
        });
    }
}
