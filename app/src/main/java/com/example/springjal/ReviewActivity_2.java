package com.example.springjal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
public class ReviewActivity_2 extends AppCompatActivity {

    // Declare your TextView and EditText fields
    TextView latitudeinput, longitudeinput;
    EditText additionaldetailsinput;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review2);
        // Initialize your TextView and EditText fields
        latitudeinput = findViewById(R.id.latitudeinput);
        longitudeinput = findViewById(R.id.longitudeinput);
        additionaldetailsinput = findViewById(R.id.additionaldetailsinput);
        image=findViewById(R.id.imageinput1);
        // Retrieve data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            String latitude = intent.getStringExtra("LATITUDE");
            String longitude = intent.getStringExtra("LONGITUDE");
            String additionalDetails = intent.getStringExtra("ADDITIONAL_DETAILS");
            String imageUrl = intent.getStringExtra("IMAGE_URL");
            // Set the retrieved data to TextView and EditText fields
            latitudeinput.setText(latitude);
            longitudeinput.setText(longitude);
            additionaldetailsinput.setText(additionalDetails);
            Picasso.get()
                    .load(imageUrl)
                    .resize(600, 800) // Provide valid positive integers for width and height
                    .centerInside()
                    .into(image);
        }
    }
}
