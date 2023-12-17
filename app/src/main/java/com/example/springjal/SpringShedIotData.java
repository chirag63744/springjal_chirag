package com.example.springjal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SpringShedIotData extends AppCompatActivity {

    EditText iotDeviceId, gaugeWaterFlowData, waterQualityData, phLevelData, overallStatusData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_shed_iot_data);

        // Initialize EditText fields
        iotDeviceId = findViewById(R.id.iotdeviceid);
        gaugeWaterFlowData = findViewById(R.id.gaugewaterflowdata);
        waterQualityData = findViewById(R.id.waterqualitydata);
        phLevelData = findViewById(R.id.phleveldata);
        overallStatusData = findViewById(R.id.overallstatusdata);

        // Get the reference to your Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("springshediotdata");
        Intent intent=getIntent();

        // Assume you have a unique device ID
        String deviceId = intent.getStringExtra("deviceid"); // Replace with your device ID

        // Attach a listener to read the data at your specified path
        myRef.child(deviceId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve data from dataSnapshot and set it to EditText fields
                if (dataSnapshot.exists()) {
                    iotDeviceId.setText(deviceId);
                    gaugeWaterFlowData.setText(dataSnapshot.child("Gaugewaterflowsensor").getValue(String.class));
                    waterQualityData.setText(dataSnapshot.child("Waterqualitysensor").getValue(String.class));
                    phLevelData.setText(dataSnapshot.child("Phlevelsensor").getValue(String.class));
                    // Set other EditText fields as needed
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Log.w("Firebase", "Failed to read value.", databaseError.toException());
            }
        });
    }
}
