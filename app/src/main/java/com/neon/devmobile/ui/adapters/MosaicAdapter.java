package com.neon.devmobile.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.neon.devmobile.R;

import java.util.ArrayList;

public class MosaicAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final ArrayList<String> arrURLs;

    public MosaicAdapter(Context context, ArrayList<String> arrURLs) {
        super(context, 0, arrURLs);
        this.context = context;
        this.arrURLs = arrURLs;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MosaicAdapter.ViewHolder holder;
        String category = arrURLs.get(position);
        if (convertView == null) {
            holder = new MosaicAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_thumbnail, parent, false);

//            holder.ivMosaic = convertView.findViewById(R.id.ivMosaic);
            convertView.setTag(holder);
        } else {
            holder = (MosaicAdapter.ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(category).into(holder.ivMosaic);
        return convertView;
    }

    static class ViewHolder {
        ImageView ivMosaic;
    }
}

