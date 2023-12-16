package com.example.springjal;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fetch_coordinates extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
//dshfbsfjlweV
    Button fetch;
    private FusedLocationProviderClient fusedLocationClient;
    private Marker currentMarker;

    // Variables to store coordinates
    private double latitude;
    private double longitude;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_coordinates);

        // Initialize the FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fetch = findViewById(R.id.fetchCoordinatesbtn);
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentMarker != null) {
                    // If a marker is present, fetch coordinates from the marker
                    LatLng markerPosition = currentMarker.getPosition();
                    latitude = markerPosition.latitude;
                    longitude = markerPosition.longitude;
                } else {
                    // If no marker is present, fetch the current location of the user
                    fetchCurrentUserLocation();
                }

                // Send coordinates to DataCollector_new_activity_2
                sendDataToNextActivity();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        // Set the height of the map to cover 85% of the screen
        ViewGroup.LayoutParams params = mapFragment.getView().getLayoutParams();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;
        params.height = (int) (screenHeight * 0.85);
        mapFragment.getView().setLayoutParams(params);

        // Get notified when the map is ready to be used.
        mapFragment.getMapAsync(this);
    }

    private void fetchCurrentUserLocation() {
        // Check if permission to access fine location is granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Permission is granted, get current location
            fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                if (location != null) {
                    // Store the current location coordinates
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                } else {
                    Toast.makeText(Fetch_coordinates.this, "Unable to fetch current location", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    private void sendDataToNextActivity() {
        // Create an Intent to start DataCollector_new_activity_2
        Intent intent = new Intent(Fetch_coordinates.this, DataCollector_new_activity_2.class);

        // Add the coordinates as extras to the Intent
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);

        // Start the new activity
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Enable My Location layer on the map
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);

        // Set up an OnMapClickListener to add a marker when the user clicks on the map
        googleMap.setOnMapClickListener(latLng -> {
            // Remove the current marker if it exists
            if (currentMarker != null) {
                currentMarker.remove();
            }

            // Add a new marker at the clicked position
            currentMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("Clicked location"));
        });

        // Check if permission to access fine location is granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Permission is granted, get current location
            fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                if (location != null) {
                    // Use the location to update the map
                    LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16));
                }
            });
        } else {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            // Permission is granted, get current location
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                        if (location != null) {
                            // Use the location to update the map
                            LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16));
                        }
                    });
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
