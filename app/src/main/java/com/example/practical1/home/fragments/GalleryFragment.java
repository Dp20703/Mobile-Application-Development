package com.example.practical1.home.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practical1.R;
import com.example.practical1.adapter.GalleryAdapter;

public class GalleryFragment extends Fragment {

    RecyclerView recyclerView;

    int[] images = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5
    };

    public GalleryFragment() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(
                R.layout.fragment_gallery,
                container,
                false
        );

        recyclerView = view.findViewById(
                R.id.galleryRecyclerView
        );

        recyclerView.setLayoutManager(
                new GridLayoutManager(
                        getContext(),
                        2
                )
        );

        GalleryAdapter adapter =
                new GalleryAdapter(images);

        recyclerView.setAdapter(adapter);

        return view;
    }
}