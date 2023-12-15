package com.example.springjal;

// ActivityModel.java
public class StatusActivityModel {
    private String activityName;
    private String approvalStatus;

    // Constructor
    public StatusActivityModel(String activityName, String approvalStatus) {
        this.activityName = activityName;
        this.approvalStatus = approvalStatus;
    }

    // Getters
    public String getActivityName() {
        return activityName;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }
}
