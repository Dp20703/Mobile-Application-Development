package com.example.practical1.home.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practical1.R;
import com.example.practical1.adapter.TaskAdapter;
import com.example.practical1.database.DatabaseHelper;
import com.example.practical1.model.Task;

import java.util.ArrayList;

public class TodoFragment extends Fragment {

    EditText taskInput;
    Button addTaskButton;
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    TaskAdapter taskAdapter;
    ArrayList<Task> taskList;


    public TodoFragment() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view =
                inflater.inflate(R.layout.fragment_todo, container, false);


        taskInput = view.findViewById(R.id.taskInput);
        addTaskButton = view.findViewById(R.id.addTaskButton);
        recyclerView = view.findViewById(R.id.taskRecyclerView);


        // Create database object
        databaseHelper = new DatabaseHelper(requireContext());

        // RecyclerView layout
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Show all tasks
        loadTasks();

        // Add task
        addTaskButton.setOnClickListener(v -> {

            String taskName = taskInput.getText().toString().trim();

            if (taskName.isEmpty()) {

                Toast.makeText(
                        getContext(),
                        "Please enter a task",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                // INSERT task into SQLite
                databaseHelper.addTask(taskName);

                Toast.makeText(
                        getContext(),
                        "Task Added",
                        Toast.LENGTH_SHORT
                ).show();

                // Clear input
                taskInput.setText("");

                // Refresh RecyclerView
                loadTasks();
            }

        });

        return view;
    }


    // VIEW ALL TASKS
    private void loadTasks() {
        taskList = databaseHelper.getAllTasks();
        taskAdapter =new TaskAdapter(taskList,databaseHelper,this::loadTasks);
        recyclerView.setAdapter(taskAdapter);
    }
}
