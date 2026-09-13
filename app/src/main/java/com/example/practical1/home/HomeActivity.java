package com.example.practical1.home;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.practical1.R;
import com.example.practical1.auth.LoginActivity;
import com.example.practical1.home.ContactActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

public class HomeActivity extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    HomePagerAdapter adapter;
    TabLayout tabLayout;
    ViewPager2 viewPager;

    String[] tabName = {
            "Employee",
            "Products",
            "Gallery",
            "Profile",
            "Todo"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Drawer
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        // Tabs
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        adapter = new HomePagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabName[position]))
                .attach();
    }

    @Override
    public boolean onNavigationItemSelected(@NotNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_add_product) {
            // open Products tab
            viewPager.setCurrentItem(1);

            Toast.makeText(
                    this,
                    "Add product from Products tab",
                    Toast.LENGTH_SHORT
            ).show();
        } else if (id == R.id.nav_update_product) {
            viewPager.setCurrentItem(1);
            Toast.makeText(
                    this,
                    "Update product from Products tab",
                    Toast.LENGTH_SHORT
            ).show();
        } else if (id == R.id.nav_contacts) {
            Intent intent = new Intent
                    (HomeActivity.this, ContactActivity.class);

            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            logout();
        }
        drawerLayout.closeDrawers();
        return true;

    }

    private void logout() {

        SharedPreferences preferences =
                getSharedPreferences("UserData",MODE_PRIVATE);

        preferences.edit()
                .putBoolean("isLoggedIn", false)
                .apply();

        Toast.makeText(
                this,"Logged Out",Toast.LENGTH_SHORT).show();

        Intent intent =
                new Intent(HomeActivity.this, LoginActivity.class);

        startActivity(intent);

        finish();
    }

}
