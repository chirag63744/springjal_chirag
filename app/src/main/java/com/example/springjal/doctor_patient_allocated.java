package com.example.springjal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class doctor_patient_allocated extends AppCompatActivity {
    RelativeLayout patientViewtest;
    EditText doctorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_patient_allocated);
        patientViewtest=findViewById(R.id.patientsinfo);
        doctorView=findViewById(R.id.search);
        doctorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),home_view_doctor_patient.class);
                startActivity(intent);
            }
        });
        patientViewtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),patient_home.class);
                startActivity(intent);
            }
        });
    }
}