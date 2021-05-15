package com.example.healthreminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void medicine(View view) {
        Toast.makeText(this, "Medicine layout is in progess", Toast.LENGTH_SHORT).show();
    }

    public void water(View view) {
        Intent intent=new Intent(this,NotifyWater.class);
        startActivity(intent);
        Toast.makeText(this, "Water layout is in progess", Toast.LENGTH_SHORT).show();
    }

    public void food(View view) {
        Toast.makeText(this, "Food layout is in progess", Toast.LENGTH_SHORT).show();
    }

    public void exercise(View view) {
        Toast.makeText(this, "Exercise layout is in progess", Toast.LENGTH_SHORT).show();
    }
}