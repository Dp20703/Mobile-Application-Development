package com.example.practical1.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.practical1.home.fragments.EmployeeFragment;
import com.example.practical1.home.fragments.GalleryFragment;
import com.example.practical1.home.fragments.ProductFragment;
import com.example.practical1.home.fragments.ProfileFragment;
import com.example.practical1.home.fragments.TodoFragment;

public class HomePagerAdapter extends FragmentStateAdapter {
    public HomePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override

    public Fragment createFragment(int position) {

        switch (position) {

            case 0:
                return new EmployeeFragment();

            case 1:
                return new ProductFragment();

            case 2:
                return new GalleryFragment();

            case 3:
                return new ProfileFragment();

            case 4:
                return new TodoFragment();

            default:
                return new EmployeeFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 5;
    }
}
