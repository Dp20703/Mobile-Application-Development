package com.example.practical1.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practical1.R;
import com.example.practical1.auth.LoginActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.splash_logo);
        TextView title = findViewById(R.id.splash_title);

        // Initially hide views
        logo.setAlpha(0f);
        title.setAlpha(0f);

        // Logo animation
        logo.animate()
                .alpha(1f)
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(1500)
                .start();

        // Text animation
        title.animate()
                .alpha(1f)
                .translationY(50f)
                .setDuration(1500)
                .setStartDelay(500)
                .start();

        // Open MainActivity after 3 seconds
        new Handler().postDelayed(() -> {

            Intent intent = new Intent(
                    SplashActivity.this,
                    LoginActivity.class
            );

            startActivity(intent);
            finish();

        }, 3000);
    }
}