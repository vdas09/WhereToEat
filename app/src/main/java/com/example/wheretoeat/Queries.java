package com.example.wheretoeat;


import java.sql.Timestamp;

public class Queries {
    public Restaurant qResult;
    public String qCuisineType;
    public int qPrice;
    public int qWalkingTime;
    public Timestamp qTimeStamp;
    public String qActionTaken;

    public Queries() {
    }

    public Queries(String qCuisineType, int qPrice, int qWalkingTime, Timestamp qTimeStamp) {
        this.qCuisineType = qCuisineType;
        this.qPrice = qPrice;
        this.qWalkingTime = qWalkingTime;
        this.qTimeStamp = qTimeStamp;
    }
    public void setResult(Restaurant qResult) {
        this.qResult = qResult;
    }
    public void setActionTaken(String qActionTaken) {
        this.qActionTaken = qActionTaken;
    }
}
