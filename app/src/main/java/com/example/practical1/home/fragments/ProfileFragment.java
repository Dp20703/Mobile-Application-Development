package com.example.practical1.home.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.practical1.R;
import com.example.practical1.auth.LoginActivity;

public class ProfileFragment extends Fragment {

    EditText name, email, age;
    RadioButton male, female;
    Button updateButton, logoutButton;
    SharedPreferences sharedPreferences;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(
                R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.profileName);
        email = view.findViewById(R.id.profileEmail);
        age = view.findViewById(R.id.profileAge);
        male = view.findViewById(R.id.profileMale);

        female = view.findViewById(R.id.profileFemale);
        updateButton = view.findViewById(R.id.updateButton);
        logoutButton = view.findViewById(R.id.logoutButton);

        sharedPreferences =
                requireActivity().getSharedPreferences("UserData", 0);

        // Load existing data
        name.setText(
                sharedPreferences.getString("employeeName", ""));
        email.setText(
                sharedPreferences.getString("employeeEmail", ""));
        age.setText(
                sharedPreferences.getString("employeeAge", ""));
        String savedGender =
                sharedPreferences.getString("employeeGender", "");

        if (savedGender.equals("Male")) {
            male.setChecked(true);
        } else if (savedGender.equals("Female")) {
            female.setChecked(true);
        }


        updateButton.setOnClickListener(v -> {

            String userName = name.getText().toString().trim();
            String userEmail = email.getText().toString().trim();
            String userAge = age.getText().toString().trim();
            String gender = "";

            if (male.isChecked()) {
                gender = "Male";
            } else if (female.isChecked()) {
                gender = "Female";
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("employeeName", userName);
            editor.putString("employeeEmail", userEmail);
            editor.putString("employeeAge", userAge);
            editor.putString("employeeGender", gender);
            editor.apply();

            Toast.makeText(
                    getContext(),
                    "Profile Updated",
                    Toast.LENGTH_SHORT
            ).show();

        });

        logoutButton.setOnClickListener(v -> {

            SharedPreferences.Editor editor =
                    sharedPreferences.edit();

            // Set login status to false
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            Toast.makeText(getContext(),
                            "Logged Out Successfully", Toast.LENGTH_SHORT)
                    .show();


            Intent intent =
                    new Intent(getActivity(), LoginActivity.class);

            startActivity(intent);

            // Close HomeActivity
            requireActivity().finish();

        });

        return view;
    }
}