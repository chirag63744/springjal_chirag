package com.example.springjal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Analytics extends AppCompatActivity {
    RelativeLayout rainfallvsspring,waterqulity;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
        rainfallvsspring =findViewById(R.id.rainfallvsspring);
        waterqulity =findViewById(R.id.waterqulity);
        rainfallvsspring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Analytics.this, BarChart_WaterQuality.class);
                startActivity(i);
            }
        });
        waterqulity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Analytics.this, BarChart_WaterQuality.class);
                startActivity(i);
            }
        });

    }
}
////gjdxfxj,,fd