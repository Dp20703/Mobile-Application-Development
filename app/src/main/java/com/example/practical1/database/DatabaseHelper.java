package com.example.practical1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.practical1.model.Task;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database information
    private static final String DATABASE_NAME = "TodoDatabase";
    private static final int DATABASE_VERSION = 1;


    // Table information
    private static final String TABLE_NAME = "Task";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TASK_NAME = "taskName";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Runs when database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TASK_NAME + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }


    // Runs when database version changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    // INSERT TASK
    public void addTask(String taskName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK_NAME, taskName);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    // VIEW ALL TASKS
    public ArrayList<Task> getAllTasks() {

        ArrayList<Task> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);


        if (cursor.moveToFirst()) {

            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));

                String taskName = cursor.getString(
                        cursor.getColumnIndexOrThrow(COLUMN_TASK_NAME));

                taskList.add(new Task(id, taskName));
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return taskList;
    }


    // DELETE TASK
    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)});
        db.close();
    }
}