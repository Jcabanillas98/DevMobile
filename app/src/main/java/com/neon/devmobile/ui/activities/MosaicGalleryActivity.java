package com.neon.devmobile.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.neon.devmobile.databinding.ActivityMosaicGalleryBinding;
import com.neon.devmobile.ui.adapters.MosaicAdapter;

import java.util.ArrayList;

public class MosaicGalleryActivity extends AppCompatActivity {

    private ActivityMosaicGalleryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMosaicGalleryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configActivity();
    }

    public void configActivity() {
        returnUpload();
        loadGallery();
    }

    public void loadGallery() {
        ArrayList<String> arrImages = new Gson().fromJson(getIntent().getExtras().getString("mosaics"), new TypeToken<ArrayList<String>>() {
        }.getType());

        binding.rvImages.setAdapter(new MosaicAdapter(arrImages));
        binding.rvImages.setLayoutManager(getSpanSize(arrImages));
    }

    public GridLayoutManager getSpanSize(ArrayList<String> arrImages) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MosaicGalleryActivity.this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int columns;
                int doubleToInt = arrImages.size() / 2;
                columns = (arrImages.size() % 2 == 0) ? 1 : position == (doubleToInt * 2) ? 2 : 1;
                return columns;
            }
        });
        return gridLayoutManager;
    }

    public void returnUpload() {
        binding.clBack.setOnClickListener(view -> onBackPressed());
    }

}