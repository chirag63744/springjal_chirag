package com.example.springjal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class UserType extends AppCompatActivity {
RelativeLayout dataCollector,dataApprover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);
        dataCollector=findViewById(R.id.datacollecter);
        dataApprover=findViewById(R.id.dataapprover);
        dataCollector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateAccountActivity("Data Collector");
            }
        });

        dataApprover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateAccountActivity("Data Approver");
            }
        });


    }
    private void openCreateAccountActivity(String userType) {
        Intent intent = new Intent(UserType.this, create_account.class);
        intent.putExtra("USER_TYPE", userType);
        startActivity(intent);
    }
}