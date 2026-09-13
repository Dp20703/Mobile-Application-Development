package com.example.practical1.home.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.practical1.R;

public class EmployeeFragment extends Fragment {
    EditText name, email, age, salary;
    RadioGroup genderGroup;
    RadioButton male, female;

    Button saveButton;
    SharedPreferences sharedPreferences;

    public EmployeeFragment() {

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_employee, container, false);

        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.employeeEmail);
        age = view.findViewById(R.id.age);
        salary = view.findViewById(R.id.salary);

        male = view.findViewById(R.id.male);
        female = view.findViewById(R.id.female);

        saveButton = view.findViewById(R.id.saveButton);


        sharedPreferences = requireActivity().getSharedPreferences("EmployeeData", 0);

        // Load previously saved employee data
        name.setText(sharedPreferences.getString("employeeName", ""));
        email.setText(sharedPreferences.getString("employeeEmail", ""));
        age.setText(sharedPreferences.getString("employeeAge", ""));
        salary.setText(sharedPreferences.getString("employeeSalary", ""));
        String savedGender = sharedPreferences.getString("employeeGender", "");

        if (savedGender.equals("Male")) {
            male.setChecked(true);
        } else if (savedGender.equals("Female")) {
            female.setChecked(true);
        }

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
                    || employeeSalary.isEmpty()
                    || gender.isEmpty()) {

                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {

                SharedPreferences.Editor editor =
                        sharedPreferences.edit();

                editor.putString(
                        "employeeName",
                        employeeName
                );

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
                        getContext(),
                        "Employee Data Saved",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });
        return view;
    }
}


