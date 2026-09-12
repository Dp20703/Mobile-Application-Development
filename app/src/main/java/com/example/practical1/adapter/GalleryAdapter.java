package com.example.practical1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practical1.R;

public class GalleryAdapter extends
        RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    int[] images;

    public GalleryAdapter(int[] images) {
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view = LayoutInflater.from(
                parent.getContext()
        ).inflate(
                R.layout.item_gallery,
                parent,
                false
        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position
    ) {

        holder.imageView.setImageResource(
                images[position]
        );
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(
                @NonNull View itemView
        ) {

            super(itemView);

            imageView = itemView.findViewById(
                    R.id.galleryImage
            );
        }
    }
}