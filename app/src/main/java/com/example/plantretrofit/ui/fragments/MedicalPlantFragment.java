package com.example.plantretrofit.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantretrofit.R;
import com.example.plantretrofit.adapter.MedicalPlantAdapter;
import com.example.plantretrofit.model.MedicalPlant;
import com.example.plantretrofit.viewmodel.MedicalPlantViewModel;

import java.util.ArrayList;
import java.util.List;

public class MedicalPlantFragment extends Fragment {
    private static final String TAG = "MedicalPlantFragment";
    private MedicalPlantViewModel viewModel;
    private RecyclerView recyclerView;
    private MedicalPlantAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_medical_plants, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // آداپتر با لیست خالی مقداردهی اولیه می‌شود
        adapter = new MedicalPlantAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MedicalPlantViewModel.class);
        viewModel.getMedicalPlants().observe(getViewLifecycleOwner(), new Observer<List<MedicalPlant>>() {
            @Override
            public void onChanged(List<MedicalPlant> plants) {
                if (plants != null) {
                    adapter.setMedicalPlants(plants); // به روزرسانی آداپتر با داده‌های جدید
                } else {
                    Log.e(TAG, "Failed to load medical plants");
                }
            }
        });


    }
}
