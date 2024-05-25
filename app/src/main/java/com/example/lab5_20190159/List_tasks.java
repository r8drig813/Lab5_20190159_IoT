package com.example.lab5_20190159;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab5_20190159.entity.Task;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class List_tasks extends AppCompatActivity {

    private TaskAdapter taskAdapter;
    private List<Task> taskList;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "TaskPrefs";
    private static final String KEY_USER_CODE = "USER_CODE";
    private TextView textViewUserCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tasks);

       // textViewUserCode = findViewById(R.id.textViewUserCode);

        String userCode = getIntent().getStringExtra("USER_CODE");
        textViewUserCode.setText("Welcome, " + userCode + "!");

    }


    private void loadTasks() {
        String tasksJson = sharedPreferences.getString("tasks", "");
        if (!tasksJson.isEmpty()) {
            // Deserialize tasks from JSON
            Gson gson = new Gson();
            Task[] tasksArray = gson.fromJson(tasksJson, Task[].class);
            taskList.addAll(Arrays.asList(tasksArray));
        }
    }
}