package com.neon.devmobile.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neon.devmobile.R;

import java.util.ArrayList;

public class MosaicAdapter extends RecyclerView.Adapter<MosaicAdapter.MosaicViewHolder> {

    private Context context;
    private final ArrayList<String> arrImages;

    public MosaicAdapter(ArrayList<String> arrImages) {
        this.arrImages = arrImages;
    }

    @NonNull
    @Override
    public MosaicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MosaicViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MosaicViewHolder holder, int position) {
        String uri = arrImages.get(position);
        Glide.with(context).load(uri).centerCrop().into(holder.ivImage);
    }

    static class MosaicViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;

        MosaicViewHolder(View view) {
            super(view);
            ivImage = view.findViewById(R.id.ivImage);
        }
    }

    @Override
    public int getItemCount() {
        return (null != arrImages ? arrImages.size() : 0);
    }

}
