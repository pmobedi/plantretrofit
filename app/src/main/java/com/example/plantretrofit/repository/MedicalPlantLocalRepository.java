package com.example.plantretrofit.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.plantretrofit.dao.MedicalPlantDao;
import com.example.plantretrofit.database.AppDatabase;
import com.example.plantretrofit.model.MedicalPlant;

import java.util.List;

public class MedicalPlantLocalRepository {
    private MedicalPlantDao medicalPlantDao;
    private LiveData<List<MedicalPlant>> allPlants;

    public MedicalPlantLocalRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        medicalPlantDao = database.medicalPlantDao();
        allPlants = medicalPlantDao.getAllPlants();
    }

    public LiveData<List<MedicalPlant>> getAllPlants() {
        return allPlants;
    }

    public void insert(MedicalPlant plant) {
        new InsertPlantAsyncTask(medicalPlantDao).execute(plant);
    }

    private static class InsertPlantAsyncTask extends AsyncTask<MedicalPlant, Void, Void> {
        private MedicalPlantDao medicalPlantDao;

        private InsertPlantAsyncTask(MedicalPlantDao medicalPlantDao) {
            this.medicalPlantDao = medicalPlantDao;
        }

        @Override
        protected Void doInBackground(MedicalPlant... plants) {
            medicalPlantDao.insert(plants[0]);
            return null;
        }
    }
}
