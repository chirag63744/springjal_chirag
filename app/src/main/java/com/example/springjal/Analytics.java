package com.example.springjal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Analytics extends AppCompatActivity {
    RelativeLayout Barchrt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
        Barchrt =findViewById(R.id.BarchartActivity);
        Barchrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Analytics.this, BarChart_WaterQuality.class);
                startActivity(i);
            }
        });

    }
}
////gjdxfxj,,fd