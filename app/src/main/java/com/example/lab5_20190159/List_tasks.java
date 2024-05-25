package com.example.lab5_20190159;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5_20190159.entity.Tarea;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class List_tasks extends AppCompatActivity {

    //private TaskAdapter taskAdapter;
    private List<Tarea> tareaList;
    public static final int ADD = 1;
    private static final String KEY_USER_CODE = "USER_CODE";
    private TextView textViewUserCode;
    public static final int EDIT = 2;
    private static final String FILE_NAME = "tarea.dat";

    private TareaAdapter tareaAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tasks);

        tareaList = loadTasks();
        tareaAdapter = new TareaAdapter(tareaList, this);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(tareaAdapter);

        FloatingActionButton fabAddTask = findViewById(R.id.fabAddTask);
        fabAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(List_tasks.this, Task_Activity.class);
            startActivityForResult(intent, ADD);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD && resultCode == RESULT_OK) {
            Tarea nuevaTarea = (Tarea) data.getSerializableExtra("tarea");
            tareaList.add(nuevaTarea);
            tareaAdapter.notifyItemInserted(tareaList.size() - 1);
            guardar(tareaList);
        } else if (requestCode == EDIT && resultCode == RESULT_OK) {
            Tarea actualizar = (Tarea) data.getSerializableExtra("tarea");
            int tareaIndex = data.getIntExtra("tareaIndex", -1);
            if (tareaIndex != -1) {
                tareaList.set(tareaIndex, actualizar);
                tareaAdapter.notifyItemChanged(tareaIndex);
                guardar(tareaList);
            }
        }
    }





    void guardar(List<Tarea> tareaList) {
        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(tareaList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Tarea> loadTasks() {
        ArrayList<Tarea> tareas = new ArrayList<>();
        try (FileInputStream fis = openFileInput(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            tareas = (ArrayList<Tarea>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tareas;
    }



}