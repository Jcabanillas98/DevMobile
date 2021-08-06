package com.neon.devmobile.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.neon.devmobile.databinding.ActivityUploadBinding;
import com.neon.devmobile.ui.adapters.ThumbnailsHorizontalAdapter;
import com.neon.devmobile.utils.ErrorUtils;
import com.neon.devmobile.utils.KeyboardUtils;

import java.util.ArrayList;

public class UploadActivity extends AppCompatActivity {

    private ActivityUploadBinding binding;
    private final ArrayList<String> arrThumbnails = new ArrayList<>();
    private final String messageMaxSize = "Solo puedes agregar hasta 6 imágenes";
    private final int maxMiniatures = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        configActivity();
    }

    public void configActivity() {
        ErrorUtils.setContext(UploadActivity.this);
        KeyboardUtils.setActivity(UploadActivity.this);
        loadActions();
        validateLoad();
    }

    public void loadActions() {
        ErrorUtils.addErrorListener(binding.etURL, binding.llURL, binding.tvErrorURL);
        KeyboardUtils.clearFocusImeDone(binding.etURL);
        initMosaicsGallery();
        returnUpload();
    }

    public void initMosaicsGallery(){
        binding.clGallery.setOnClickListener(view -> startActivity(new Intent(UploadActivity.this, MosaicGalleryActivity.class).putExtra("mosaics", new Gson().toJson(arrThumbnails))));
    }

    public void returnUpload() {
        binding.clBack.setOnClickListener(view -> onBackPressed());
    }

    public void validateLoad() {
        binding.llLoadImage.setOnClickListener(view -> {
            if (validField()) {
                KeyboardUtils.hideKeyboard();
                binding.etURL.clearFocus();
                if (arrThumbnails.size() < maxMiniatures)
                    addMiniature(binding.etURL.getText().toString().trim());
                else
                    Toast.makeText(this, messageMaxSize, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addMiniature(String uri) {
        arrThumbnails.add(uri);
        Glide.with(UploadActivity.this).load(uri).centerCrop().into(binding.ivImage);
        binding.rvThumbnails.setAdapter(new ThumbnailsHorizontalAdapter(arrThumbnails, binding.ivImage));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UploadActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.rvThumbnails.setLayoutManager(linearLayoutManager);
    }

    public boolean validField() {
        ErrorUtils.setError(binding.llURL, binding.tvErrorURL, false);
        return isValidURL();
    }

    public boolean isValidURL() {
        if (ErrorUtils.isEmpty(binding.etURL)) {
            ErrorUtils.setError(binding.llURL, binding.tvErrorURL, true);
            KeyboardUtils.toggleKeyboard(binding.etURL);
            return false;
        }
        return true;
    }

}