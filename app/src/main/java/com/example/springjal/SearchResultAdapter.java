package com.example.springjal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.springjal.RecievedActivityModel;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ActivityViewHolder> {

    private List<SearchResultModel> activities;
    private Context context;

    public SearchResultAdapter(List<SearchResultModel> activities,Context context) {
        this.activities = activities != null ? activities : new ArrayList<>();
        this.context = context;
    }

    // ViewHolder for each item
    public static class ActivityViewHolder extends RecyclerView.ViewHolder {
        // Declare views from activity_card_view.xml
        TextView activityNameTextView;
        TextView villagenametextview;
        RelativeLayout viewdatabtn;


        public ActivityViewHolder(View itemView) {
            super(itemView);
            // Initialize views
            activityNameTextView = itemView.findViewById(R.id.activityNameTextView);
            villagenametextview = itemView.findViewById(R.id.activityStatusTextView);
            viewdatabtn = itemView.findViewById(R.id.viewDatabtn);
        }
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the card view layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_card, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        // Bind data to views for each activity item
        SearchResultModel activity = activities.get(position);
        holder.activityNameTextView.setText(activity.getActivityName());
        holder.villagenametextview.setText(activity.getApprovalStatus());

        // Handle button actions here (Review/Edit, Approve, Reject)
        holder.viewdatabtn.setOnClickListener(v -> {
            // Handle Review/Edit button click
            // Implement your logic here
            fetchActivityData(activity.getActivityName());
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
                Toast.makeText(context, "Activity data not found", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(context, "Failed to fetch activity data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(context, ReviewActivity.class);
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
        context.startActivity(intent);
    }



    @Override
    public int getItemCount() {
        return activities.size();
    }
}

