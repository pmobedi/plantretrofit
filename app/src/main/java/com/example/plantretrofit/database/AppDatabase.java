package com.example.plantretrofit.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.plantretrofit.dao.MedicalPlantDao;
import com.example.plantretrofit.model.MedicalPlant;

@Database(entities = {MedicalPlant.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MedicalPlantDao medicalPlantDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "MedicalPlantsDB.db")
                    .createFromAsset("MedicalPlantsDB.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
