package com.sample.fahrschuleapp;

public class DrivingStudent extends Person{

    private char drivingLicenceType;
    private String infoInstructor;
    private int price;

    public DrivingStudent(String name, String surName, int age, String bday, String phonenumber, char gender,
                          String ID, String email, char drivingLicenceType, String infoInstructor, int price) {
        super(name, surName, age, bday, phonenumber, gender, ID, email);

        this.price = price;
        this.drivingLicenceType = drivingLicenceType;
        this.infoInstructor = infoInstructor;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getInfoInstructor() {
        return infoInstructor;
    }

    public void setInfoInstructor(String infoInstructor) {
        this.infoInstructor = infoInstructor;
    }


    public char getDrivingLicenceType() {
        return drivingLicenceType;
    }

    public void setDrivingLicenceType(char drivingLicenceType) {
        this.drivingLicenceType = drivingLicenceType;
    }

}
