package com.example.loginapplication;

public class CustomerSales {

    int cusID;
    String firstName;
    String lastName;
    String address;
    int salesID;
    int salesPrice;
    int salesInterest;

    public CustomerSales(int cusID, String firstName, String lastName, String address, int salesID, int salesPrice, int salesInterest) {
        this.cusID = cusID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.salesID = salesID;
        this.salesPrice = salesPrice;
        this.salesInterest = salesInterest;
    }

    public CustomerSales(String firstName, String lastName, int salesPrice, int salesInterest) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salesPrice = salesPrice;
        this.salesInterest = salesInterest;
    }

    public int getCusID() {
        return cusID;
    }

    public void setCusID(int cusID) {
        this.cusID = cusID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public void setSalesPrice(int salesPrice) {
        this.salesPrice = salesPrice;
    }

    public int getSalesInterest() {
        return salesInterest;
    }

    public void setSalesInterest(int salesInterest) {
        this.salesInterest = salesInterest;
    }
}
