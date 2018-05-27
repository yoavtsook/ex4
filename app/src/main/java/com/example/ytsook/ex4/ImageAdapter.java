package com.example.ytsook.ex4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private ArrayList<String> mDataSource;

    public ImageAdapter(ArrayList<String> items) {
        this.mDataSource = items;
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    public void setmDataSource(ArrayList<String> newDataSource) {
        this.mDataSource = newDataSource;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgView;
        public View view;

        public ViewHolder(View view) {
            super(view);
            imgView = view.findViewById(R.id.image);
            this.view = view;
        }
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.img_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int pos) {
        final String url = mDataSource.get(pos);

        Picasso.with(holder.imgView.getContext()).load(url).into(holder.imgView);
    }
}
