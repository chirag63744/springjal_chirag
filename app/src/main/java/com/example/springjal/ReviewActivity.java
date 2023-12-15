package com.example.springjal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ReviewActivity extends AppCompatActivity {
    TextView activityid;
    // Declare your EditText fields
    EditText statenameinput, districtnameinput, villagenameinput, benefeciaryinput, statusinput, dateofsurveyinput, iotdeviceidinput;
    RelativeLayout nextbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        activityid=findViewById(R.id.activityName);
        // Initialize your EditText fields
        statenameinput = findViewById(R.id.statenameinput);
        districtnameinput = findViewById(R.id.districtnameinput);
        villagenameinput = findViewById(R.id.villagenameinput);
        benefeciaryinput = findViewById(R.id.benefeciaryinput);
        statusinput = findViewById(R.id.statusinput);
        dateofsurveyinput = findViewById(R.id.dateofsurveyinput);
        iotdeviceidinput = findViewById(R.id.iotdeviceidinput);
        nextbtn = findViewById(R.id.nextbtn);
        // Retrieve data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            String activityName = intent.getStringExtra("ACTIVITY_ID");
            String state = intent.getStringExtra("STATE");
            String district = intent.getStringExtra("DISTRICT");
            String village = intent.getStringExtra("VILLAGE");
            String beneficiary = intent.getStringExtra("BENEFICIARY");
            String status = intent.getStringExtra("STATUS");
            String dateOfSurvey = intent.getStringExtra("DATE_OF_SURVEY");
            String iotDeviceId = intent.getStringExtra("IOT_DEVICE_ID");

            // Set the retrieved data to EditText fields
            activityid.setText(activityName);
            statenameinput.setText(state);
            districtnameinput.setText(district);
            villagenameinput.setText(village);
            benefeciaryinput.setText(beneficiary);
            statusinput.setText(status);
            dateofsurveyinput.setText(dateOfSurvey);
            iotdeviceidinput.setText(iotDeviceId);
        }
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getIntent();
                String imageUrl = i.getStringExtra("IMAGE_URL");
                String latitude = i.getStringExtra("LATITUDE");
                String longitude = i.getStringExtra("LONGITUDE");
                String additionalDetails = i.getStringExtra("ADDITIONAL_DETAILS");
                Intent new_i = new Intent(ReviewActivity.this, ReviewActivity_2.class);
                new_i.putExtra("IMAGE_URL", imageUrl);
                new_i.putExtra("ADDITIONAL_DETAILS", additionalDetails);
                new_i.putExtra("LATITUDE", latitude);
                new_i.putExtra("LONGITUDE", longitude);
                startActivity(new_i);
            }
        });
    }
}
