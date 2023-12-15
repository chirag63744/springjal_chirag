package com.example.springjal;

public class SearchResultModel {
    private String activityName;
    private String villagename;

    // Constructor
    public SearchResultModel(String activityName, String villagename) {
        this.activityName = activityName;
        this.villagename = villagename;
    }

    // Getters
    public String getActivityName() {
        return activityName;
    }

    public String getApprovalStatus() {
        return villagename;
    }
}
