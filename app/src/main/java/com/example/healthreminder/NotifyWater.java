package com.example.healthreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class NotifyWater extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressStatus = 0;
    Handler handler=new Handler();
    NotificationCompat.Builder notification;
    private static final int uniqueID = 40001;
    private static final String Channel_Id = "abc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_water);

        notification = new NotificationCompat.Builder(this, Channel_Id);
        notification.setAutoCancel(true);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        incrementProgress();
       // notifyUser();

    }

    public void incrementProgress(){
        if(progressStatus<100){
            progressStatus+=10;
        }
        progressBar.setProgress(progressStatus);
        /*new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/

    }

    public void notifyUser(){notification.setContentText("Notification Content");
        notification.setContentTitle("Title");
        //notification.setSmallIcon(R.drawable.reminder_icon);
        notification.setTicker("Ticker");
        notification.setWhen(System.currentTimeMillis());

        Intent intent = new Intent(this, MainActivity.class);


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);
        createNotificationChannel();

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID, notification.build());

    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(Channel_Id, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}