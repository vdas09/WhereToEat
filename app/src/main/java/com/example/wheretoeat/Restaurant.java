package com.example.wheretoeat;

public class Restaurant {
    public String restName;
    public String restLocation;
    public String restPrice;
    public double restRating;
    public String restCuisineType;
    public String restWebsite;
    public String restPhoneNumber;

    public Restaurant() {

    }

    public Restaurant(String restName, String restLocation, String restPrice, double restRating, String restCuisineType, String restWebsite, String restPhoneNumber) {
        this.restName = restName;
        this.restLocation = restLocation;
        this.restPrice = restPrice;
        this.restRating = restRating;
        this.restCuisineType = restCuisineType;
        this.restWebsite = restWebsite;
        this.restPhoneNumber = restPhoneNumber;
    }
}
