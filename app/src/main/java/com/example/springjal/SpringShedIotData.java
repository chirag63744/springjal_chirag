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

    EditText iotDeviceId, gaugeWaterFlowData, waterQualityData, turbidityEditText, phLevelEditText,
            temperatureEditText, conductivityEditText, dissolvedOxygenEditText, gasValueEditText,
            hardnessEditText, solidsEditText, statusEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_shed_iot_data);

        iotDeviceId = findViewById(R.id.iotdeviceid);
        gaugeWaterFlowData = findViewById(R.id.gaugewaterflowdata);
       // waterQualityData = findViewById(R.id.waterqualitydata);
        turbidityEditText = findViewById(R.id.turbidityedittextdisplay);
        phLevelEditText = findViewById(R.id.phlevelrledittextdisplay);
        temperatureEditText = findViewById(R.id.temperatureedittextinput);
        conductivityEditText = findViewById(R.id.conductivityEdittextDisplay);
        dissolvedOxygenEditText = findViewById(R.id.disssolvedoxygenedittextdisplay);
        gasValueEditText = findViewById(R.id.gasvalueedittxt);
        hardnessEditText = findViewById(R.id.hardnessEdittextDatadisplay);
        solidsEditText = findViewById(R.id.solidsedittextdisplay);
        statusEditText = findViewById(R.id.statusedittextDisplay);

        // Get the reference to your Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("springshediotdata");
        Intent intent=getIntent();

        // Assume you have a unique device ID
        String deviceId = intent.getStringExtra("deviceid"); // Replace with your device ID
        // getting Data in Strings
        String iotDeviceIdValue = iotDeviceId.getText().toString();
        String gaugeWaterFlowDataValue = gaugeWaterFlowData.getText().toString();
        // String waterQualityDataValue = waterQualityData.getText().toString(); // Uncomment if needed
        String turbidityEditTextValue = turbidityEditText.getText().toString();
        String phLevelEditTextValue = phLevelEditText.getText().toString();
        String temperatureEditTextValue = temperatureEditText.getText().toString();
        String conductivityEditTextValue = conductivityEditText.getText().toString();
        String dissolvedOxygenEditTextValue = dissolvedOxygenEditText.getText().toString();
        String gasValueEditTextValue = gasValueEditText.getText().toString();
        String hardnessEditTextValue = hardnessEditText.getText().toString();
        String solidsEditTextValue = solidsEditText.getText().toString();
        String statusEditTextValue = statusEditText.getText().toString();

        // Attach a listener to read the data at your specified path
        myRef.child(deviceId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve data from dataSnapshot and set it to EditText fields
                if (dataSnapshot.exists()) {
                    iotDeviceId.setText(deviceId);
                    gaugeWaterFlowData.setText(dataSnapshot.child("WaterFlow").getValue(String.class));
                  //  waterQualityData.setText(dataSnapshot.child("WaterQuality").getValue(String.class));
                    turbidityEditText.setText(dataSnapshot.child("Turbidity").getValue(String.class));
                    phLevelEditText.setText(dataSnapshot.child("pHLevel").getValue(String.class));
                    temperatureEditText.setText(dataSnapshot.child("Temperature").getValue(String.class));
                    conductivityEditText.setText(dataSnapshot.child("ConductivityTDS").getValue(String.class));
                    dissolvedOxygenEditText.setText(dataSnapshot.child("DissolvedOxygen").getValue(String.class));
                    gasValueEditText.setText(dataSnapshot.child("GasSensor").getValue(String.class));
                    hardnessEditText.setText(dataSnapshot.child("Hardness").getValue(String.class));
                    solidsEditText.setText(dataSnapshot.child("Solids").getValue(String.class));
                    statusEditText.setText(dataSnapshot.child("OverallStatus").getValue(String.class));
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
