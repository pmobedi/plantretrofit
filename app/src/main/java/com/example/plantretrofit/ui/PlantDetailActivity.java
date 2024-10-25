package com.example.plantretrofit.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.plantretrofit.R;
import com.example.plantretrofit.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PlantDetailActivity extends AppCompatActivity {

    private static final String TAG = "PlantDetailActivity";
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private int plantId;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        // دریافت plantId از Intent
        plantId = getIntent().getIntExtra("plantId", -1);
        Log.d(TAG, "Received plantId: " + plantId);

        if (plantId != -1) {
            // تنظیم ViewPagerAdapter با plantId
            viewPagerAdapter = new ViewPagerAdapter(this, plantId);
            viewPager.setAdapter(viewPagerAdapter);

            // تنظیم TabLayoutMediator
            new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
                switch (position) {
                    case 0:
                        tab.setText("توضیحات");
                        break;
                    case 1:
                        tab.setText("ترکیبات شیمیایی");
                        break;
                    case 2:
                        tab.setText("کشاورزی");
                        break;
                }
            }).attach();
        } else {
            Log.e(TAG, "Invalid plantId received.");
            // نمایش پیغام خطا یا برگشت به صفحه قبلی
            finish();
        }
    }
}
