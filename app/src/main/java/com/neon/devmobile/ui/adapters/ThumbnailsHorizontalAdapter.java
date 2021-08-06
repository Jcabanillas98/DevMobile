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

public class ThumbnailsHorizontalAdapter extends RecyclerView.Adapter<ThumbnailsHorizontalAdapter.ThumbnailsViewHolder> {

    private Context context;
    private final ArrayList<String> arrThumbnails;
    private final ImageView display;

    public ThumbnailsHorizontalAdapter(ArrayList<String> arrThumbnails, ImageView display) {
        this.arrThumbnails = arrThumbnails;
        this.display = display;
    }

    @NonNull
    @Override
    public ThumbnailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ThumbnailsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_thumbnail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbnailsViewHolder holder, int position) {
        String uri = arrThumbnails.get(position);
        Glide.with(context).load(uri).centerCrop().into(holder.ivThumbnails);
        holder.ivThumbnails.setOnClickListener(view -> Glide.with(context).load(uri).into(display));
        holder.ivRemove.setOnClickListener(view -> {
            removePosition(position);
            if (arrThumbnails.size() == 0)
                Glide.with(context).clear(display);
        });
    }

    static class ThumbnailsViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnails, ivRemove;

        ThumbnailsViewHolder(View view) {
            super(view);
            ivThumbnails = view.findViewById(R.id.ivThumbnails);
            ivRemove = view.findViewById(R.id.ivRemove);
        }
    }

    @Override
    public int getItemCount() {
        return (null != arrThumbnails ? arrThumbnails.size() : 0);
    }

    public void removePosition(int position) {
        arrThumbnails.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, arrThumbnails.size());
    }
}
