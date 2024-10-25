package com.example.plantretrofit.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantretrofit.model.MedicalPlant;
import com.example.plantretrofit.repository.MedicalPlantApiRepository;
import com.example.plantretrofit.repository.MedicalPlantLocalRepository;

import java.util.List;

public class MedicalPlantViewModel extends AndroidViewModel {
    private MutableLiveData<List<MedicalPlant>> medicalPlants;
    private MutableLiveData<MedicalPlant> medicalPlant;
    private MedicalPlantApiRepository apiRepository;
    private MedicalPlantLocalRepository localRepository;


    public MedicalPlantViewModel(Application application) {
        super(application);  // اضافه شدن این خط
        apiRepository = new MedicalPlantApiRepository();
        localRepository = new MedicalPlantLocalRepository(application);
        medicalPlants = new MutableLiveData<>();
        medicalPlant = new MutableLiveData<>();
    }

    public LiveData<List<MedicalPlant>> getMedicalPlants() {
        if (medicalPlants.getValue() == null || medicalPlants.getValue().isEmpty()) {
            localRepository.getAllPlants().observeForever(plants -> {
                if (plants != null && !plants.isEmpty()) {
                    medicalPlants.setValue(plants);
                    Log.d("MedicalPlantViewModel", "Loaded from Room: " + plants);
                } else {
                    apiRepository.getMedicalPlants().observeForever(apiPlants -> {
                        medicalPlants.setValue(apiPlants);
                        Log.d("MedicalPlantViewModel", "Loaded from API: " + apiPlants);
                    });
                }
            });
        }
        return medicalPlants;
    }


    // اضافه کردن متد برای دریافت یک MedicalPlant با استفاده از شناسه
    public LiveData<MedicalPlant> getMedicalPlantById(int plantId) {
        return apiRepository.getMedicalPlantById(plantId);
    }
}
