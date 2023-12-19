package com.example.springjal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

public class SpringPlotsMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_plots_maps);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Fetch data from Firestore and add markers
        fetchDataFromFirestore();
    }

    private void fetchDataFromFirestore() {
        db.collection("activities")
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        // Handle errors
                        return;
                    }

                    if (value != null) {
                        for (DocumentChange doc : value.getDocumentChanges()) {
                            if (doc.getType() == DocumentChange.Type.ADDED) {
                                // Extract latitude and longitude
                                double latitude = Double.parseDouble(doc.getDocument().getString("latitude"));
                                double longitude = Double.parseDouble(doc.getDocument().getString("longitude"));

                                // Get the document ID
                                String documentId = doc.getDocument().getId();

                                // Add a marker to the map
                                addMarkerToMap(latitude, longitude, documentId);
                            }
                        }
                    }
                });
    }

    private void addMarkerToMap(double latitude, double longitude, String documentId) {
        if (mMap != null) {
            LatLng location = new LatLng(latitude, longitude);
            Marker marker = mMap.addMarker(new MarkerOptions().position(location).title(documentId));

            // Optionally, move the camera to the marker
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        // Set the initial camera position to India
        LatLng indiaLatLng = new LatLng(20.5937, 78.9629); // Latitude and Longitude of India
        mMap.moveCamera(CameraUpdateFactory.newLatLng(indiaLatLng));

        // Optionally, you can set a specific zoom level
        float zoomLevel = 5.0f; // You can adjust the zoom level as needed
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(indiaLatLng, zoomLevel));

        // Add a marker at the center of India
        mMap.addMarker(new MarkerOptions().position(indiaLatLng).title("Marker in India"));
    }

}