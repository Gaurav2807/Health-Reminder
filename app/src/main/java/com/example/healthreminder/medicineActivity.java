package com.example.healthreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class medicineActivity extends AppCompatActivity {

    LinearLayout layout ;
    EditText e1,e2,e3,e4;
    Button addButton,setAlarm;
    TextView text;
    int hour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        layout = findViewById(R.id.listID);
        addButton = findViewById(R.id.addView);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView();
            }
        });
    }

    public void addView(){
        final View layoutInflate = getLayoutInflater().inflate(R.layout.listview_medicine,null,false);
        e1 = (EditText) layoutInflate.findViewById(R.id.medicineNAME);
        e2 = (EditText) layoutInflate.findViewById(R.id.dosage);
        e3 = (EditText) layoutInflate.findViewById(R.id.hour);
        e4 = (EditText) layoutInflate.findViewById(R.id.minutes);

       text = (TextView) layoutInflate.findViewById(R.id.submit);
       setAlarm = (Button) layoutInflate.findViewById(R.id.setAlarm);

       setAlarm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (TextUtils.isEmpty(e3.getText().toString()) || TextUtils.isEmpty(e4.getText().toString())) {
                   Toast.makeText(getBaseContext(), "ENTER TIME", Toast.LENGTH_SHORT).show();
               } else {
                   int dose = Integer.valueOf(e2.getText().toString());
                   hour = Integer.valueOf(e3.getText().toString());
                   int min = Integer.valueOf(e4.getText().toString());
                   int noOfDose = 24 / dose;
                   Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                   intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
                   intent.putExtra(AlarmClock.EXTRA_MINUTES, min);
                   intent.putExtra(AlarmClock.EXTRA_MESSAGE, e1.getText().toString());
                   intent.putExtra(AlarmClock.EXTRA_ALARM_SNOOZE_DURATION, noOfDose);
                   intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
                   intent.putExtra(AlarmClock.EXTRA_VIBRATE, true);
                   if (hour < 24 && hour > 0 && min < 60 && min > 0) {
                       startActivity(intent);
                   } else {
                       Toast.makeText(getBaseContext(), "ENTER IN RAILWAY TIME", Toast.LENGTH_LONG).show();
                   }
               }
           }
       });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(e1.getText().toString()) || TextUtils.isEmpty(e2.getText().toString()) || TextUtils.isEmpty(e3.getText().toString()) || TextUtils.isEmpty(e4.getText().toString())){
                    Toast.makeText(getBaseContext(), "Provide Full Details", Toast.LENGTH_SHORT).show();
                }
                else {

                    e1.setEnabled(false);
                    e2.setEnabled(false);
                    e3.setEnabled(false);
                    e4.setEnabled(false);


                    String str = text.getText().toString();
                    if (str.equals("Submit")) {
                        text.setText("Delete");
                    } else {
                        layout.removeView(layoutInflate);
                        Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
                        startActivity(intent);
                    }
                }
            }
        });

        layout.addView(layoutInflate);

    }

}