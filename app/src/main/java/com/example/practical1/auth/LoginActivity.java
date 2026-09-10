package com.example.practical1.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.example.practical1.R;
import com.example.practical1.home.HomeActivity;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button loginButton;
    SharedPreferences sharedPreferences;

    String correctEmail = "user@user.com";
    String correctPassword = "user@12345";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);

        // check if user is already logged in
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        loginButton.setOnClickListener(v -> {
            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString().trim();

            // Check credentials
            if (userEmail.equals(correctEmail) && userPassword.equals(correctPassword)) {

                // save login status
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.apply();

                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);

                startActivity(intent);
                finish();
            } else {

                Toast.makeText(
                        LoginActivity.this,
                        "Invalid Email or Password",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}