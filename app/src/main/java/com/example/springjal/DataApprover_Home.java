package com.example.springjal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class DataApprover_Home extends AppCompatActivity {
RelativeLayout previouslyAccepted,recievedActivity,springshedsearch_by_id,spring_shed_data;
    private TextView usernameTextView;
    private TextView roleTextView;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_approver_home);
        recievedActivity=findViewById(R.id.recievedActivity);
        previouslyAccepted=findViewById(R.id.previous_activity);
        springshedsearch_by_id=findViewById(R.id.spring_details_by_id);
        spring_shed_data=findViewById(R.id.spring_plots);
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
        recievedActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),RecievedActivity.class);
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