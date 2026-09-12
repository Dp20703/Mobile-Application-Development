package com.example.practical1.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.practical1.home.fragments.EmployeeFragment;
import com.example.practical1.home.fragments.GalleryFragment;
import com.example.practical1.home.fragments.ProductFragment;
import com.example.practical1.home.fragments.ProfileFragment;

public class HomePagerAdapter extends FragmentStateAdapter {
    public HomePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new EmployeeFragment();
        } else if (position == 1) {
            return new ProductFragment();
        } else if (position == 2) {
            return new GalleryFragment();
        } else {
            return new ProfileFragment();
        }
    }
    @Override
    public  int getItemCount(){
        return 4;
    }
}
