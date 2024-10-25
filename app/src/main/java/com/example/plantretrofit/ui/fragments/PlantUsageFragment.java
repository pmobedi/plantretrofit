package com.example.plantretrofit.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.plantretrofit.R;
import com.example.plantretrofit.model.MedicalPlant;
import com.example.plantretrofit.viewmodel.MedicalPlantViewModel;
import com.example.plantretrofit.viewmodel.SharedMedicalPlantViewModel;

public class PlantUsageFragment extends Fragment {

    private SharedMedicalPlantViewModel medicalPlantViewModel;
    private TextView usageDescriptionTextView, usageMethodsTextView, usagePrecautionsTextView;
    private static final String ARG_PLANT_ID = "plantId";
    private int plantId;

    public static PlantUsageFragment newInstance(int plantId) {
        PlantUsageFragment fragment = new PlantUsageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PLANT_ID, plantId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant_usage, container, false);

        // پیدا کردن TextView ها
        usageDescriptionTextView = view.findViewById(R.id.usage_description);
        usageMethodsTextView = view.findViewById(R.id.usage_methods);
        usagePrecautionsTextView = view.findViewById(R.id.usage_precautions);

        if (getArguments() != null) {
            plantId = getArguments().getInt(ARG_PLANT_ID);
            Log.d("PlantUsageFragment", "Received plantId: " + plantId);
        }

        medicalPlantViewModel = new ViewModelProvider(requireActivity()).get(SharedMedicalPlantViewModel.class);
        medicalPlantViewModel.getMedicalPlantById(plantId).observe(getViewLifecycleOwner(), new Observer<MedicalPlant>() {
            @Override
            public void onChanged(MedicalPlant medicalPlant) {
                if (medicalPlant != null) {
                    usageDescriptionTextView.setText("بیماری ها: " + medicalPlant.getDisease());
                    usageMethodsTextView.setText("گل دهی: " + medicalPlant.getFlowring());
                    usagePrecautionsTextView.setText("خصوصیات " + medicalPlant.getProperties());
                }
            }
        });

        return view;
    }
}
