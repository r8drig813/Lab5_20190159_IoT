package com.example.lab5_20190159;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUserCode;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUserCode = findViewById(R.id.editTextUserCode);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userCode = editTextUserCode.getText().toString();
                if (!userCode.isEmpty()) {
                    SharedPreferences sharedPrefs = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPrefs.edit();
                    editor.putString("USER_CODE", userCode);
                    editor.apply();

                    Intent intent = new Intent(MainActivity.this, List_tasks.class);
                    intent.putExtra("USER_CODE", userCode);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter your PUCP code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}