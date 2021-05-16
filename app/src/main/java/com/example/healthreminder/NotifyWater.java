package com.example.healthreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Calendar;

public class NotifyWater extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressStatus = 0;
    Handler handler=new Handler();
    NotificationCompat.Builder notification;
    private static final int uniqueID = 40001;
    private static final String Channel_Id = "abc";
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    Bitmap mybitmap;
    Drawable myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_water);

        myImage= ResourcesCompat.getDrawable(getResources(),R.drawable.water,null);
        mybitmap=((BitmapDrawable) myImage).getBitmap();

        notification = new NotificationCompat.Builder(this, Channel_Id);
        notification.setAutoCancel(true);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        incrementProgress();

       //scheduleNotification();
       //notifyUser();

    }

    public void incrementProgress(){
        if(progressStatus<100){
            progressStatus+=10;
        }
        progressBar.setProgress(progressStatus);

        if(progressStatus==100){
            //add poppers;
        }

    }


    public void notifyUser(){
        notification.setContentText("It's water time.");
        notification.setContentTitle("Hydrate Yourself");
        notification.setSmallIcon(R.drawable.water);
        notification.setTicker("Ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(mybitmap));

        Intent intent = new Intent(this, NotifyWater.class);


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

    public void scheduleNotification(){
        alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotifyWater.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

       // Set the alarm to start at 11:30 a.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 30);

           // setRepeating() lets you specify a precise custom interval--in this case,
           // 2 minutes.
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 2, alarmIntent);

    }

    public void buttonClick(View view) {
        notifyUser();
        incrementProgress();
        scheduleNotification();
    }
}