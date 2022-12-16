package com.example.loginapplication;

public class Cars {

    String carVin;
    String make;
    String model;
    int year;
    int miles;

    public Cars(String carVin, String make, String model, int year, int miles) {
        this.carVin = carVin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.miles = miles;
    }

    public String getCarVin() {
        return carVin;
    }

    public void setCarVin(String carVin) {
        this.carVin = carVin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }
}

