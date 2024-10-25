package com.example.plantretrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.plantretrofit.ui.PlantDetailActivity;
import com.example.plantretrofit.R;
import com.example.plantretrofit.model.MedicalPlant;

import java.util.List;

public class MedicalPlantAdapter extends RecyclerView.Adapter<MedicalPlantAdapter.MedicalPlantViewHolder> {

    private List<MedicalPlant> medicalPlants;
    private static final String TAG = "MedicalPlantsAdapter";
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(MedicalPlant medicalPlant);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public MedicalPlantAdapter(Context context, List<MedicalPlant> medicalPlants) {
        this.context = context;
        this.medicalPlants = medicalPlants;
    }

    public void setMedicalPlants(List<MedicalPlant> medicalPlants) {
        this.medicalPlants = medicalPlants;
        Log.d("MedicalPlantAdapter", "Setting new plants: " + medicalPlants.size());
        notifyDataSetChanged(); // به‌روزرسانی لیست پس از تغییرات
    }

    @NonNull
    @Override
    public MedicalPlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medical_plant, parent, false);
        return new MedicalPlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalPlantViewHolder holder, int position) {
        MedicalPlant plant = medicalPlants.get(position);

        // نمایش نام گیاه در ویوها
        holder.plantName.setText(plant.getPlantsName());
        holder.scName.setText(plant.getScientificName());

        Log.d("MedicalPlantAdapter", "Binding plant: " + plant.getPlantsName());

        holder.itemView.setOnClickListener(v -> {
            // شروع PlantDetailActivity
            Intent intent = new Intent(context, PlantDetailActivity.class);
            intent.putExtra("plantId", plant.getId());  // ارسال id گیاه
            context.startActivity(intent);
            // چاپ id گیاه در لاگ
            Log.d("PlantClick", "Clicked plant id: " + plant.getId());

            // فراخوانی listener اگر وجود دارد
            if (listener != null) {
                listener.onItemClick(plant);
            }
        });


    }

    @Override
    public int getItemCount() {
        return medicalPlants != null ? medicalPlants.size() : 0;
    }

    public static class MedicalPlantViewHolder extends RecyclerView.ViewHolder {
        TextView plantName, scName;

        public MedicalPlantViewHolder(@NonNull View itemView) {
            super(itemView);
            plantName = itemView.findViewById(R.id.plant_name);
            scName = itemView.findViewById(R.id.scientific_name);
        }
    }
}
