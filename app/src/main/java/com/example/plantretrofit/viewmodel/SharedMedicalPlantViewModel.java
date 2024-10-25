package com.example.plantretrofit.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.plantretrofit.model.MedicalPlant;
import com.example.plantretrofit.repository.MedicalPlantApiRepository;

import java.util.List;

public class SharedMedicalPlantViewModel extends ViewModel {
    private MutableLiveData<List<MedicalPlant>> medicalPlants;
    private MutableLiveData<MedicalPlant> selectedPlant; // برای نگهداری یک گیاه خاص
    private MedicalPlantApiRepository repository;

    public SharedMedicalPlantViewModel() {
        repository = new MedicalPlantApiRepository();
        medicalPlants = new MutableLiveData<>();
        selectedPlant = new MutableLiveData<>(); // مقداردهی اولیه
    }

    public LiveData<List<MedicalPlant>> getMedicalPlants() {
        if (medicalPlants.getValue() == null || medicalPlants.getValue().isEmpty()) {
            loadMedicalPlants(); // درخواست داده‌ها از Repository
        }
        return medicalPlants;
    }

    private void loadMedicalPlants() {
        Log.d("SharedMedicalPlantViewModel", "Loading medical plants from repository...");
        repository.getMedicalPlants().observeForever(new Observer<List<MedicalPlant>>() {
            @Override
            public void onChanged(List<MedicalPlant> plants) {
                medicalPlants.setValue(plants);  // بروزرسانی LiveData با داده‌های دریافتی
                Log.d("SharedMedicalPlantViewModel", "Loaded medical plants: " + plants);
            }
        });
    }

    // این متد برای دریافت اطلاعات یک گیاه خاص بر اساس plantId است
    public LiveData<MedicalPlant> getMedicalPlantById(int plantId) {
        selectedPlant = repository.getMedicalPlantById(plantId); // استفاده از repository برای دریافت داده‌ها
        return selectedPlant; // بازگرداندن داده‌های LiveData
    }
}
