package com.example.springjal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.springjal.StatusActivityModel;
import java.util.ArrayList;
import java.util.List;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ActivityViewHolder> {

    private List<StatusActivityModel> activities;
    private Context context;

    public StatusAdapter(List<StatusActivityModel> activities) {
        this.activities = activities != null ? activities : new ArrayList<>();
    }

    // ViewHolder for each item
    public static class ActivityViewHolder extends RecyclerView.ViewHolder {
        // Declare views from activity_card_view.xml
        TextView activityNameTextView;
        TextView activityStatusTextView;
        Button reviewEditButton;
        Button uploadButton;
        Button withdrawButton;

        public ActivityViewHolder(View itemView) {
            super(itemView);
            // Initialize views
            activityNameTextView = itemView.findViewById(R.id.activityNameTextView);
            activityStatusTextView = itemView.findViewById(R.id.activityStatusTextView);
            reviewEditButton = itemView.findViewById(R.id.reviewEditButton);
            uploadButton = itemView.findViewById(R.id.uploadButton);
            withdrawButton = itemView.findViewById(R.id.withdrawButton);
        }
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the card view layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.status_card_view, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        // Bind data to views for each activity item
        StatusActivityModel activity = activities.get(position);
        holder.activityNameTextView.setText(activity.getActivityName());
        holder.activityStatusTextView.setText(activity.getApprovalStatus());

        // Handle button actions here (Review/Edit, Upload, Withdraw)
        holder.reviewEditButton.setOnClickListener(v -> {
            // Handle Review/Edit button click
            // Implement your logic here
        });

        holder.uploadButton.setOnClickListener(v -> {
            // Handle Upload button click
            // Implement your logic here
        });

        holder.withdrawButton.setOnClickListener(v -> {
            // Handle Withdraw button click
            // Implement your logic here
        });
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }
}
