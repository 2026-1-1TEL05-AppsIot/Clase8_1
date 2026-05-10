package com.example.clase81;

import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.clase81.databinding.ActivityNotificationBinding;

public class NotificationActivity extends AppCompatActivity {

    ActivityNotificationBinding binding;

    private static final  String CANAL_DEFAULT = "canal_importance_default";
    private static final int NOTIFICATION_ID = 1;
    private static final int PERMISSION_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        crearCanalesNotificacion();

        binding.btnNoteDefault.setOnClickListener(view -> {
            notificarImportanceDefault();
        });

    }

    public void crearCanalesNotificacion() {

        NotificationChannel channel = new NotificationChannel(CANAL_DEFAULT,
                "Canal notificaciones default",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("Canal para notificaciones con prioridad default");
        channel.enableVibration(true);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        pedirPermisos();
    }

    public void pedirPermisos() {
        // TIRAMISU = 33
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ActivityCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(NotificationActivity.this, new String[]{POST_NOTIFICATIONS}, PERMISSION_REQUEST_CODE);
        }
    }

    public void notificarImportanceDefault(){

        //Crear notificación
        //Agregar información a la notificación que luego sea enviada a la actividad que se abre
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("pid","1612");
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE);
        //
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CANAL_DEFAULT)
                .setSmallIcon(R.drawable.baseline_rocket_launch_24)
                .setContentTitle("Mi primera notificación")
                .setContentText("Esta es mi primera notificación en Android :D")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        Notification notification = builder.build();

        //Lanzar notificación
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        if (ActivityCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(NOTIFICATION_ID, notification);
        }

    }
}