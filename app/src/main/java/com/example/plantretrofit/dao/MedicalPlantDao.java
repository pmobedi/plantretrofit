package com.example.plantretrofit.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.plantretrofit.model.MedicalPlant;

import java.util.List;

@Dao
public interface MedicalPlantDao {

    @Insert
    void insert(MedicalPlant plant);

    @Update
    void update(MedicalPlant plant);

    @Delete
    void delete(MedicalPlant plant);

    @Query("SELECT * FROM MedicalPlants")
    LiveData<List<MedicalPlant>> getAllPlants();

    @Query("SELECT * FROM MedicalPlants WHERE id = :plantId")
    LiveData<MedicalPlant> getPlantById(int plantId);
}
