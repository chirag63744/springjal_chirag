package com.example.springjal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataCollector_Home extends AppCompatActivity {
RelativeLayout newActivity,resumeActivity,status,openmaps;
    private TextView usernameTextView;
    private TextView roleTextView;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collector);
        newActivity=findViewById(R.id.new_activity);
        resumeActivity=findViewById(R.id.resume_activity);
        status=findViewById(R.id.check_status);
        openmaps=findViewById(R.id.open_maps);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Find TextViews by their IDs
        usernameTextView = findViewById(R.id.username);
        roleTextView = findViewById(R.id.role);

        // Assuming you have a users node in your Firebase Realtime Database
        userRef = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid());

        // Add a ValueEventListener to fetch user data
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Get the user's name and role from the snapshot
                    String username = dataSnapshot.child("name").getValue().toString();
                    String role = dataSnapshot.child("role").getValue().toString();

                    // Set the retrieved values in the TextViews
                    usernameTextView.setText(username);
                    roleTextView.setText("Role: " + role);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle potential errors here
            }
        });
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