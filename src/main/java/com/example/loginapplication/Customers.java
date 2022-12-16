package com.example.loginapplication;

public class Customers {

    int cusID;
    String firstName;
    String lastName;
    String address;

    public Customers(int cusID, String firstName, String lastName, String address) {
        this.cusID = cusID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
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
}

