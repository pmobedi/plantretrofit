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

public class PlantPropertiesFragment extends Fragment {

    private SharedMedicalPlantViewModel medicalPlantViewModel;
    private TextView lightRequirementsTextView, waterRequirementsTextView, soilTypeTextView, fertilizerRequirementsTextView, temperatureRangeTextView;
    private static final String ARG_PLANT_ID = "plantId";
    private int plantId;

    public static PlantPropertiesFragment newInstance(int plantId) {
        PlantPropertiesFragment fragment = new PlantPropertiesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PLANT_ID, plantId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant_properties, container, false);

        // پیدا کردن TextView ها

        waterRequirementsTextView = view.findViewById(R.id.water_requirements);
        soilTypeTextView = view.findViewById(R.id.soil_type);
        fertilizerRequirementsTextView = view.findViewById(R.id.fertilizer_requirements);


        if (getArguments() != null) {
            plantId = getArguments().getInt(ARG_PLANT_ID);
            Log.d("PlantPropertiesFragment", "Received plantId: " + plantId);
        }

        medicalPlantViewModel = new ViewModelProvider(requireActivity()).get(SharedMedicalPlantViewModel.class);
        medicalPlantViewModel.getMedicalPlantById(plantId).observe(getViewLifecycleOwner(), new Observer<MedicalPlant>() {
            @Override
            public void onChanged(MedicalPlant medicalPlant) {
                if (medicalPlant != null) {

                    waterRequirementsTextView.setText("نیازهای آبی: " + medicalPlant.getWaterRequirements());
                    soilTypeTextView.setText("نوع خاک: " + medicalPlant.getSoilType());
                    fertilizerRequirementsTextView.setText("نیازهای کود: " + medicalPlant.getCodeNeeds());

                }
            }
        });

        return view;
    }
}
