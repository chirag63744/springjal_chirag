package com.example.springjal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class DataCollector_new_activity_1 extends AppCompatActivity {
RelativeLayout nextbtn;
    private EditText stateNameInput, districtNameInput, villageNameInput, beneficiaryInput, statusInput, dateOfSurveyInput, iotDeviceIdInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collector_new1);
        // Find EditText fields by their IDs
        stateNameInput = findViewById(R.id.statenameinput);
        districtNameInput = findViewById(R.id.districtnameinput);
        villageNameInput = findViewById(R.id.villagenameinput);
        beneficiaryInput = findViewById(R.id.benefeciaryinput);
        statusInput = findViewById(R.id.statusinput);
        dateOfSurveyInput = findViewById(R.id.dateofsurveyinput);
        iotDeviceIdInput = findViewById(R.id.iotdeviceidinput);
        nextbtn=findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the entered values from EditText fields
                String state = stateNameInput.getText().toString();
                String district = districtNameInput.getText().toString();
                String village = villageNameInput.getText().toString();
                String beneficiary = beneficiaryInput.getText().toString();
                String status = statusInput.getText().toString();
                String dateOfSurvey = dateOfSurveyInput.getText().toString();
                String iotDeviceId = iotDeviceIdInput.getText().toString();

                // Create an Intent to pass values to the next activity
                Intent intent = new Intent(DataCollector_new_activity_1.this, DataCollector_new_activity_2.class);

                // Pass values to the next activity using intent extras
                intent.putExtra("STATE", state);
                intent.putExtra("DISTRICT", district);
                intent.putExtra("VILLAGE", village);
                intent.putExtra("BENEFICIARY", beneficiary);
                intent.putExtra("STATUS", status);
                intent.putExtra("DATE_OF_SURVEY", dateOfSurvey);
                intent.putExtra("IOT_DEVICE_ID", iotDeviceId);
                // Start the next activity
                startActivity(intent);
            }
        });
    }
}