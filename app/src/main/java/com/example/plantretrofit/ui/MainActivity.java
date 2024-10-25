package com.example.plantretrofit.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private MedicalPlantAdapter adapter;
    private MedicalPlantViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // آداپتر با لیست خالی مقداردهی اولیه می‌شود
        adapter = new MedicalPlantAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // مقداردهی ViewModel
        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MedicalPlantViewModel.class);

        // مشاهده تغییرات در داده‌ها
        viewModel.getMedicalPlants().observe(this, new Observer<List<MedicalPlant>>() {
            @Override
            public void onChanged(List<MedicalPlant> medicalPlants) {
                if (medicalPlants != null && !medicalPlants.isEmpty()) {
                    adapter.setMedicalPlants(medicalPlants);
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "No data to display.");
                }
            }
        });
    }
}
