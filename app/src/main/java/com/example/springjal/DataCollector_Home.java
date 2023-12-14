package com.example.springjal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class DataCollector_Home extends AppCompatActivity {
RelativeLayout newActivity,resumeActivity,status,openmaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collector);
        newActivity=findViewById(R.id.new_activity);
        resumeActivity=findViewById(R.id.resume_activity);
        status=findViewById(R.id.check_status);
        openmaps=findViewById(R.id.open_maps);
        newActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),DataCollector_new_activity_1.class);
                startActivity(intent);
            }
        });
        resumeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),StatusDataCollector.class);
                startActivity(intent);
            }
        });
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),StatusDataCollector.class);
                startActivity(intent);
            }
        });
        openmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SpringPlotsMapsActivity.class);
                startActivity(intent);
            }
        });
    }
}