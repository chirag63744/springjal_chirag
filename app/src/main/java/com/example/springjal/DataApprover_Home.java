package com.example.springjal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class DataApprover_Home extends AppCompatActivity {
RelativeLayout previouslyAccepted,recievedActivity,springshedsearch_by_id,spring_shed_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_approver_home);
        recievedActivity=findViewById(R.id.recievedActivity);
        previouslyAccepted=findViewById(R.id.previous_activity);
        springshedsearch_by_id=findViewById(R.id.spring_details_by_id);
        spring_shed_data=findViewById(R.id.spring_plots);
        recievedActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),DataCollector_new_activity_1.class);
                startActivity(intent);
            }
        });
        previouslyAccepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),StatusDataCollector.class);
                startActivity(intent);
            }
        });
        springshedsearch_by_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SearchUsingId.class);
                startActivity(intent);
            }
        });
        spring_shed_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SpringPlotsMapsActivity.class);
                startActivity(intent);
            }
        });

    }
}