package com.example.lab5_20190159;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab5_20190159.entity.Tarea;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class List_tasks extends AppCompatActivity {

    //private TaskAdapter taskAdapter;
    private List<Tarea> tareaList;
    public static final int ADD = 1;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "TaskPrefs";
    private static final String KEY_USER_CODE = "USER_CODE";
    private TextView textViewUserCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tasks);

       // textViewUserCode = findViewById(R.id.textViewUserCode);

        //String userCode = getIntent().getStringExtra("USER_CODE");
        //textViewUserCode.setText("Welcome, " + userCode + "!");

        FloatingActionButton fabAddTask = findViewById(R.id.fabAddTask);
        fabAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(List_tasks.this, Task_Activity.class);
            startActivityForResult(intent, ADD);
        });

    }


    private void loadTasks() {
        String tasksJson = sharedPreferences.getString("tasks", "");
        if (!tasksJson.isEmpty()) {
            // Deserialize tasks from JSON
            Gson gson = new Gson();
            Tarea[] tasksArray = gson.fromJson(tasksJson, Tarea[].class);
            tareaList.addAll(Arrays.asList(tasksArray));
        }
    }
}