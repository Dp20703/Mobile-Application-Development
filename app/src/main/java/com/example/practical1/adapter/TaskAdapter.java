package com.example.practical1.adapter;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practical1.R;
import com.example.practical1.database.DatabaseHelper;
import com.example.practical1.model.Task;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    ArrayList<Task> taskList;
    DatabaseHelper databaseHelper;
    Runnable refreshTasks;


    public TaskAdapter(ArrayList<Task> taskList, DatabaseHelper databaseHelper, Runnable refreshTasks) {
        this.taskList = taskList;
        this.databaseHelper = databaseHelper;
        this.refreshTasks = refreshTasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {
        Task task = taskList.get(position);

        holder.taskName.setText(task.getTaskName());
        holder.deleteButton
                .setOnClickListener(v -> {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Delete Task")
                            .setMessage("Do you want to delete this task?")
                            .setPositiveButton("Yes", (dialog, which)
                                    -> {
                                databaseHelper.deleteTask(task.getId());
                                // Refresh RecyclerView
                                refreshTasks.run();
                            })
                            .setNegativeButton("No", null)
                            .show();

                });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskName;
        Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            taskName = itemView.findViewById(R.id.taskName);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
