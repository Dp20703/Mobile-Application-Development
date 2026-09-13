package com.example.practical1.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practical1.R;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact);

        if (getSupportActionBar() != null) {
            getSupportActionBar()
                    .setTitle("Contact List");
        }
    }
}