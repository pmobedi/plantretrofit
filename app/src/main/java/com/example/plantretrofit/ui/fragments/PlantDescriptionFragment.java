package com.example.plantretrofit.ui.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.plantretrofit.R;
import com.example.plantretrofit.model.MedicalPlant;
import com.example.plantretrofit.viewmodel.SharedMedicalPlantViewModel;

public class PlantDescriptionFragment extends Fragment {
    private static final String TAG = "PlantDescriptionFragment";
    private int plantId;
    private SharedMedicalPlantViewModel sharedViewModel;
    private ImageView plantImageView;
    private TextView descriptionTextView;
    private TextView scientificNameTextView;

    public static PlantDescriptionFragment newInstance(int plantId) {
        PlantDescriptionFragment fragment = new PlantDescriptionFragment();
        Bundle args = new Bundle();
        args.putInt("plantId", plantId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            plantId = getArguments().getInt("plantId");
            Log.d(TAG, "PlantId in fragment: " + plantId);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant_description, container, false);
        plantImageView = view.findViewById(R.id.plant_image);
        descriptionTextView = view.findViewById(R.id.plant_description);
        scientificNameTextView = view.findViewById(R.id.scientific_name);

        // دریافت ViewModel
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedMedicalPlantViewModel.class);

        // فراخوانی داده‌های مرتبط با plantId
        sharedViewModel.getMedicalPlantById(plantId).observe(getViewLifecycleOwner(), new Observer<MedicalPlant>() {
            @Override
            public void onChanged(MedicalPlant medicalPlant) {
                if (medicalPlant != null) {
                    // نمایش جزئیات گیاه
                    descriptionTextView.setText(medicalPlant.getDescription());
                    scientificNameTextView.setText(medicalPlant.getScientificName());

                    // بارگذاری تصویر از بایت‌ها به Bitmap و تنظیم آن در ImageView
                    byte[] imgBlob = medicalPlant.getImg();
                    if (imgBlob != null && imgBlob.length > 0) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imgBlob, 0, imgBlob.length);
                        plantImageView.setImageBitmap(bitmap);
                    }
                } else {
                    Log.d(TAG, "No plant details available for plantId: " + plantId);
                }
            }
        });

        return view;
    }
}
