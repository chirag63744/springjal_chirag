package com.example.springjal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class AnalyseSpringBoundaries extends AppCompatActivity {
ImageView image1,image2;
RelativeLayout analysebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyse_spring_boundaries);
        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);
        analysebtn=findViewById(R.id.analysebtn);
        Intent in=getIntent();
        String imageurl=in.getStringExtra("imageurl");
        Picasso.get().load(imageurl).into(image1);
        Picasso.get().load(imageurl).into(image2);
        analysebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Bhas Kar bhai", Toast.LENGTH_SHORT).show();            }
        });
    }
}