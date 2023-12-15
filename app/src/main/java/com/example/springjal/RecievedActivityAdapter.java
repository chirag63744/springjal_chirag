package com.example.springjal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.springjal.RecievedActivityModel;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class RecievedActivityAdapter extends RecyclerView.Adapter<RecievedActivityAdapter.ActivityViewHolder> {

    private List<RecievedActivityModel> activities;
    private Context context;

    public RecievedActivityAdapter(List<RecievedActivityModel> activities,Context context) {
        this.activities = activities != null ? activities : new ArrayList<>();
        this.context = context;
    }

    // ViewHolder for each item
    public static class ActivityViewHolder extends RecyclerView.ViewHolder {
        // Declare views from activity_card_view.xml
        TextView activityNameTextView;
        TextView activityStatusTextView;
        Button reviewEditButton;
        Button approvebtn;
        Button rejectbtn;

        public ActivityViewHolder(View itemView) {
            super(itemView);
            // Initialize views
            activityNameTextView = itemView.findViewById(R.id.activityNameTextView);
            activityStatusTextView = itemView.findViewById(R.id.activityStatusTextView);
            reviewEditButton = itemView.findViewById(R.id.reviewEditButton);
            approvebtn = itemView.findViewById(R.id.approvebtn);
            rejectbtn = itemView.findViewById(R.id.rejectbtn);
        }
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the card view layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recieved_activity_card_layout, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        // Bind data to views for each activity item
        RecievedActivityModel activity = activities.get(position);
        holder.activityNameTextView.setText(activity.getActivityName());
        holder.activityStatusTextView.setText(activity.getApprovalStatus());

        // Handle button actions here (Review/Edit, Approve, Reject)
        holder.reviewEditButton.setOnClickListener(v -> {
            // Handle Review/Edit button click
            // Implement your logic here
        });

        holder.approvebtn.setOnClickListener(v -> {
            // Handle Approve button click
            updateApprovalStatus(activity.getActivityName(), "Approved");
        });

        holder.rejectbtn.setOnClickListener(v -> {
            // Handle Reject button click
            updateApprovalStatus(activity.getActivityName(), "Rejected");
        });
    }
    private void updateApprovalStatus(String activityId, String status) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference activityRef = db.collection("activities").document(activityId);

        activityRef.update("approvalStatus", status)
                .addOnSuccessListener(aVoid -> {
                    // Update successful
                    Toast.makeText(context, "Status updated successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Update failed
                    Toast.makeText(context, "Failed to update status: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }


    @Override
    public int getItemCount() {
        return activities.size();
    }
}

