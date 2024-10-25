package com.example.plantretrofit.api;



import com.example.plantretrofit.model.MedicalPlant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("medical-plants/") // URL endpoint for fetching medical plants
    Call<List<MedicalPlant>> getMedicalPlants();

    @GET("medical-plants/{id}")
    Call<MedicalPlant> getMedicalPlantById(@Path("id") int plantId);
}
