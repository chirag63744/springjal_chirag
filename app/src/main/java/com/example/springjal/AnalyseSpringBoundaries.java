package com.example.springjal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AnalyseSpringBoundaries extends AppCompatActivity {
ImageView image1,image2,preprocessimg1,preproceessimg2;
RelativeLayout analysebtn;
    String imageurl;
TextView changeAre;
    String areaString,link;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyse_spring_boundaries);
        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);
        preprocessimg1=findViewById(R.id.preprocessedimage1);
        preproceessimg2=findViewById(R.id.image2preprocessed);
        changeAre=findViewById(R.id.chgedArea);
        analysebtn=findViewById(R.id.analysebtn);
        Intent in=getIntent();
         imageurl=in.getStringExtra("imageurl");
        Picasso.get().load(imageurl).into(image1);
        Picasso.get().load(imageurl).into(image2);
        analysebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FlaskRequestTask().execute(imageurl);
                new FlaskRequestTaskImg().execute(imageurl);

            }
        });
    }
    private class FlaskRequestTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String imageUrl = params[0];
            String serverUrl = "https://flask-production-5149.up.railway.app/analyze";
            String Imagefetch = "https://flask-production-5149.up.railway.app/process_image";// Replace with your server's IP address

            try {
                URL url = new URL(serverUrl);
                URL urlimg = new URL(Imagefetch);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Create JSON payload
                String jsonInputString = "{\"image_url\": \"" + imageUrl + "\"}";

                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                // Get response from the Flask server
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    return response.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // Handle the Flask server response here
            // For simplicity, we will just print the result to the console
            System.out.println("Flask Server Response: " + result);
            try {
                // Parse the JSON string
                JSONObject jsonObject = new JSONObject(result);

                // Fetch the "area" data
                double area = jsonObject.getDouble("area");

                // Convert the area to a string
                 areaString = Double.toString(area);

                // Print the result
                System.out.println("Area: " + areaString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            changeAre.setText(areaString);
        }

    }
    private class FlaskRequestTaskImg extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String imageUrl = params[0];
            String serverUrl = "https://flask-production-5149.up.railway.app/process_image";// Replace with your server's IP address

            try {
                URL url = new URL(serverUrl);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Create JSON payload
                String jsonInputString = "{\"image_url\": \"" + imageUrl + "\"}";

                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                // Get response from the Flask server
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    return response.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // Handle the Flask server response here
            // For simplicity, we will just print the result to the console
            System.out.println("Flask Server Response: " + result);
            try {
                // Parse the JSON string
                JSONObject jsonObject = new JSONObject(result);

                // Fetch the "area" data
                 link = jsonObject.getString("output_image_url");
                Picasso.get().load(link).into(preprocessimg1);
                Picasso.get().load(link).into(preproceessimg2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            changeAre.setText(areaString);
        }

    }
}