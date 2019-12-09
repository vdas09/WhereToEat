package com.example.wheretoeat;


import java.sql.Timestamp;

public class Queries {
    public Restaurant qResult;
    public String qCuisineType;
    public String qUser;
    public int qPrice;
    //public int qWalkingTime;
    public Timestamp qTimeStamp;
    public String qActionTaken;

    public Queries() {
    }

    public Queries(String qCuisineType, int qPrice, Timestamp qTimeStamp, String qUser) {
        this.qCuisineType = qCuisineType;
        this.qPrice = qPrice;
        //this.qWalkingTime = qWalkingTime;
        this.qTimeStamp = qTimeStamp;
        this.qUser = qUser;
    }
    public void setResult(Restaurant qResult) {
        this.qResult = qResult;
    }
    public void setActionTaken(String qActionTaken) {
        this.qActionTaken = qActionTaken;
    }
}
