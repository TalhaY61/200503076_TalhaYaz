package com.sample.fahrschuleapp;

public class Person {

    private String name;
    private String surName;
    private int age;
    private String phonenumber;
    private char gender;
    private String ID;
    public String email;

    public Person(String name, String surName, int age, String phonenumber, char gender, String ID, String email) {
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.phonenumber = phonenumber;
        this.gender = gender;
        this.ID = ID;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        this.ID = iD;
    }
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public String getPhoneNumber() {
        return phonenumber;
    }
    public void setPhoneNumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getSurName() {
        return surName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

