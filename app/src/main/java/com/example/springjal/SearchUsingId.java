package com.example.springjal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class SearchUsingId extends AppCompatActivity {
RelativeLayout fetchbtn,fetchIotSensor,analysSpringbtn;
EditText springshedId;
String springid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_using_id);
        fetchbtn=findViewById(R.id.fetchdata);
        fetchIotSensor=findViewById(R.id.fetchIotdata);
        analysSpringbtn=findViewById(R.id.analyseadmin);
        springshedId=findViewById(R.id.springshedidedittext);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            // Check if the user is an admin
            checkAdminStatus(userId);
        }
        fetchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 springid=springshedId.getText().toString();
                fetchActivityData(springid);
            }
        });
        fetchIotSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SpringShedIotData.class);
                springid=springshedId.getText().toString();
                intent.putExtra("deviceid", springid);
                startActivity(intent);

            }
        });
        analysSpringbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String springid=springshedId.getText().toString();

                sendDatatoAnalyseSpringActivity(springid);
            }
        });
    }

    private void sendDatatoAnalyseSpringActivity(String springid) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference activityRef = db.collection("activities").document(springid);

        activityRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {

                String imageUrl = documentSnapshot.getString("imageUrl");
                Intent new_analyseactivityintent=new Intent(this,AnalyseSpringBoundaries.class);
                new_analyseactivityintent.putExtra("imageurl",imageUrl);
                startActivity(new_analyseactivityintent);
            } else {
                Toast.makeText(this, "Activity data not found", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Failed to fetch activity data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private void checkAdminStatus(String userId) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String role = dataSnapshot.child("role").getValue(String.class);
                    // Check if the user has the role of an admin
                    if (role != null && role.equals("Data Approver")) {
                        // User is an admin, show the admin button
                        analysSpringbtn.setVisibility(View.VISIBLE);
                    } else {
                        // User is not an admin, hide the admin button
                        analysSpringbtn.setVisibility(View.GONE);
                    }
                } else {
                    // Handle the case where user data does not exist
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Toast.makeText(SearchUsingId.this, "Failed to check user role: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchActivityData(String activityId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference activityRef = db.collection("activities").document(activityId);

        activityRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String additionalDetails = documentSnapshot.getString("additionalDetails");
                String approvalStatus = documentSnapshot.getString("approvalStatus");
                String beneficiary = documentSnapshot.getString("beneficiary");
                String dateOfSurvey = documentSnapshot.getString("dateOfSurvey");
                String district = documentSnapshot.getString("district");
                String imageUrl = documentSnapshot.getString("imageUrl");
                String iotDeviceId = documentSnapshot.getString("iotDeviceId");
                String latitude = documentSnapshot.getString("latitude");
                String longitude = documentSnapshot.getString("longitude");
                String state = documentSnapshot.getString("state");
                String status = documentSnapshot.getString("status");
                String village = documentSnapshot.getString("village");

                // Now you have retrieved all the fields from Firestore
                // You can use this data as needed, such as displaying it in a view or passing it to another activity
                // For instance, if you need to start an activity with these details:
                startReviewActivityWithDetails(activityId, additionalDetails, approvalStatus, beneficiary, dateOfSurvey, district, imageUrl, iotDeviceId, latitude, longitude, state, status, village);
            } else {
                Toast.makeText(this, "Activity data not found", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Failed to fetch activity data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }


    private void startReviewActivityWithDetails(
            String activityId,
            String additionalDetails,
            String approvalStatus,
            String beneficiary,
            String dateOfSurvey,
            String district,
            String imageUrl,
            String iotDeviceId,
            String latitude,
            String longitude,
            String state,
            String status,
            String village
    ) {
        Intent intent = new Intent(this, ReviewActivity.class);
        intent.putExtra("ACTIVITY_ID", activityId);
        intent.putExtra("ADDITIONAL_DETAILS", additionalDetails);
        intent.putExtra("APPROVAL_STATUS", approvalStatus);
        intent.putExtra("BENEFICIARY", beneficiary);
        intent.putExtra("DATE_OF_SURVEY", dateOfSurvey);
        intent.putExtra("DISTRICT", district);
        intent.putExtra("IMAGE_URL", imageUrl);
        intent.putExtra("IOT_DEVICE_ID", iotDeviceId);
        intent.putExtra("LATITUDE", latitude);
        intent.putExtra("LONGITUDE", longitude);
        intent.putExtra("STATE", state);
        intent.putExtra("STATUS", status);
        intent.putExtra("VILLAGE", village);
        // Add other necessary data to be passed to ReviewActivity if needed
         startActivity(intent);
    }
}