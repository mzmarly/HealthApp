package com.example.demo.model.BasicUserDataInfo;

public enum PhysicalActivity {
    LESS_ACTIVE(1.3), //Less than an average of 30 minutes a day
    FAIRLY_ACTIVE(1.6), //An average of 30-59 minutes a day
    ACTIVE_ACROSS_THE_WEEK(1.9), //An average of 60 minutes or more a day but not every day
    ACTIVE_EVERY_DAY(2.2); //60 minutes or more a day every day
    private double activityRatio;

    public double getActivityRatio() {
        return activityRatio;
    }

    PhysicalActivity(double activityRatio){
        this.activityRatio=activityRatio;
    }
}
