package com.example.practical1.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practical1.R;
import com.example.practical1.auth.LoginActivity;

public class HomeActivity extends AppCompatActivity {
    EditText name, email, age, salary;
    RadioGroup genderGroup;
    RadioButton male, female;

    Button saveButton, logoutButton;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        name = findViewById(R.id.name);
        email = findViewById(R.id.employeeEmail);
        age = findViewById(R.id.age);
        salary = findViewById(R.id.salary);

        genderGroup = findViewById(R.id.genderGroup);

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);


        saveButton = findViewById(R.id.saveButton);
        logoutButton = findViewById(R.id.logoutButton);

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);


        // Save employee data

        saveButton.setOnClickListener(v -> {
            String employeeName =
                    name.getText().toString().trim();

            String employeeEmail =
                    email.getText().toString().trim();

            String employeeAge =
                    age.getText().toString().trim();

            String employeeSalary =
                    salary.getText().toString().trim();

            String gender = "";

            if (male.isChecked()) {
                gender = "Male";
            } else if (female.isChecked()) {
                gender = "Female";
            }

            if (employeeName.isEmpty()
                    || employeeEmail.isEmpty()
                    || employeeAge.isEmpty()
                    || gender.isEmpty()
                    || employeeSalary.isEmpty()) {

                Toast.makeText(
                        HomeActivity.this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                ).show();

            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("employeeName", employeeName);
                editor.putString(
                        "employeeEmail",
                        employeeEmail
                );

                editor.putString(
                        "employeeAge",
                        employeeAge
                );

                editor.putString(
                        "employeeGender",
                        gender
                );

                editor.putString(
                        "employeeSalary",
                        employeeSalary
                );
                editor.apply();

                Toast.makeText(
                        HomeActivity.this,
                        "Employee Data Saved",
                        Toast.LENGTH_SHORT
                ).show();
            }


        });

        // logout

        logoutButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor =
                    sharedPreferences.edit();

            editor.putBoolean("isLoggedIn", false);

            editor.apply();

            Intent intent = new Intent(
                    HomeActivity.this,
                    LoginActivity.class
            );

            startActivity(intent);
            finish();
        });
    }

}
