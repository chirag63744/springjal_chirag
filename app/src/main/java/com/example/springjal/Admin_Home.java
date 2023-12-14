package com.example.springjal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Admin_Home extends AppCompatActivity {
RelativeLayout recievedActivities,SearchSpringData,analyseSpringBoundaries,Analytics,springPlot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        recievedActivities=findViewById(R.id.recievedActivity);
        SearchSpringData=findViewById(R.id.spring_data_filter);
        analyseSpringBoundaries=findViewById(R.id.analysespringshed);
        Analytics=findViewById(R.id.analytics);
        springPlot=findViewById(R.id.spring_plot_all);
        recievedActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),DataCollector_new_activity_1.class);
                startActivity(intent);
            }
        });
        analyseSpringBoundaries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AnalyseSpringBoundaries.class);
                startActivity(intent);
            }
        });
        Analytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Analytics.class);
                startActivity(intent);
            }
        });
        springPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), SpringPlotsMapsActivity.class);
                startActivity(intent);
            }
        });
        SearchSpringData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SearchSpringshedData.class);
                startActivity(intent);
            }
        });
    }
}