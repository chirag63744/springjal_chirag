package com.example.springjal;


public class RecievedActivityModel {
    private String activityName;
    private String approvalStatus;

    // Constructor
    public RecievedActivityModel(String activityName, String approvalStatus) {
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
