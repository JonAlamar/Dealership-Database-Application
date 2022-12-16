package com.example.loginapplication;

public class Sales {

    int salesID;
    int salesPrice;
    int salesInterest;
    String carVin;
    int cusID;

    public Sales(int salesID, int salesPrice, int salesInterest) {

        this.salesID = salesID;
        this.salesPrice = salesPrice;
        this.salesInterest = salesInterest;
    }

    public Sales(int salesID, int salesPrice, int salesInterest, String carVin, int cusID) {
        this.salesID = salesID;
        this.salesPrice = salesPrice;
        this.salesInterest = salesInterest;
        this.carVin = carVin;
        this.cusID = cusID;
    }

    public int getSalesID() {
        return salesID;
    }

    public void setSalesID(int salesID) {
        this.salesID = salesID;
    }

    public int getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(int salesprice) {
        this.salesPrice = salesprice;
    }

    public int getSalesInterest() {
        return salesInterest;
    }

    public void setSalesInterest(int salesInterest) {
        this.salesInterest = salesInterest;
    }

    public String getCarVin() {
        return carVin;
    }

    public void setCarVin(String carVin) {
        this.carVin = carVin;
    }

    public int getCusID() {
        return cusID;
    }

    public void setCusID(int cusID) {
        this.cusID = cusID;
    }
}
