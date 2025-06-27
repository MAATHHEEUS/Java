package com.app.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class screen_init extends AppCompatActivity {

    Button goToSearch, goToSave;

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_init);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if(ContextCompat.checkSelfPermission(screen_init.this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(screen_init.this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }

        goToSave = (Button) findViewById(R.id.SIbutton_gotosave);
        goToSearch = (Button) findViewById(R.id.SIbutton_gotosearch);

        DB.initDB(this);
        DB.initTable(this);
        DB.closeDB();

        cursor = DB.query(this);

        if(cursor.getCount() != 0){
            for(int i = 0; i < cursor.getCount(); i++){
                int ibirth = cursor.getColumnIndex("birth");
                String date = new SimpleDateFormat("dd/MM", Locale.getDefault()).format(new Date());
                if(date.equals(cursor.getString(ibirth).substring(0, 5))){
                    int inome = cursor.getColumnIndex("nome");
                    makeNotification(cursor.getString(inome));
                }
                cursor.moveToNext();
            }
        }
    }

        public void makeNotification(String nome) {
            String chanelID = "CHANNEL_ID_NOTIFICATION";

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), chanelID);
            builder.setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText("Hoje é o dia do aniversário de "+nome)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            Intent it = new Intent(getApplicationContext(), MainActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, it, PendingIntent.FLAG_MUTABLE);
            builder.setContentIntent(pi);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel nc = notificationManager.getNotificationChannel(chanelID);
                if(nc == null) {
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    nc = new NotificationChannel(chanelID, "Something", importance);
                    nc.setLightColor(Color.BLUE);
                    notificationManager.createNotificationChannel(nc);
                }
            }

            notificationManager.notify(0, builder.build());
        }

    public void openSearch(View v) {
        Intent ScreenSearch = new Intent(this, com.app.agenda.ScreenSearch.class);
        startActivity(ScreenSearch);
    }

    public void openSave(View v) {
        Intent ScreenSave = new Intent(this, com.app.agenda.MainActivity.class);
        startActivity(ScreenSave);
    }
}