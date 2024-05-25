package com.example.lab5_20190159;

import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Task_Activity extends AppCompatActivity {
    String channelId = "channelDefaultPri";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        createNotificationChannel();
        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonCancel = findViewById(R.id.buttonCancel);
        // Agrega un OnClickListener al botón
        buttonSave.setOnClickListener(v -> {
            launchNotification();

            Intent intent = new Intent(Task_Activity.this, List_tasks.class);
            startActivity(intent);

        });

        buttonCancel.setOnClickListener(v -> {
            launchNotification1();

            Intent intent = new Intent(Task_Activity.this, List_tasks.class);
            startActivity(intent);
        });


    }

    public void createNotificationChannel() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Canal notificaciones default", NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("Canal para notificaciones con prioridad default");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(channel);

            askPermission();
        }
    }

    private void askPermission() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ActivityCompat.checkSelfPermission(this, POST_NOTIFICATIONS) ==
                        PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(Task_Activity.this,
                    new String[]{POST_NOTIFICATIONS},
                    101);
        }
    }

    private void launchNotification() {
        Intent intent = new Intent(this, Task_Activity.class);
        PendingIntent pendingIntent = PendingIntent. getActivity(this, 0, intent,
                PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_add_task_24)
                .setContentTitle("Se creo Tarea!")
                .setContentText("Se ha guardado exitosamente :D" )
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat. from(this);
        if (ActivityCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED)
        {
            notificationManager.notify(1, builder.build());
        }

    }

    private void launchNotification1() {
        Intent intent = new Intent(this, Task_Activity.class);
        PendingIntent pendingIntent = PendingIntent. getActivity(this, 0, intent,
                PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_add_task_24)
                .setContentTitle("Se cancelo la tarea!")
                .setContentText("No se creo la tarea :c" )
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat. from(this);
        if (ActivityCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED)
        {
            notificationManager.notify(1, builder.build());
        }

    }
}