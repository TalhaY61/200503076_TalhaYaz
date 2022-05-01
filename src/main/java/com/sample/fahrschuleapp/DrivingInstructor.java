package com.sample.fahrschuleapp;

public class DrivingInstructor extends Person{

    private int salary;
    private String password;

    public DrivingInstructor(String name, String surName, int age, String bday, String phonenumber, char gender,
                             String ID, String email, int salary) {
        super(name, surName, age, bday, phonenumber, gender, ID, email);
        this.salary = salary;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
}
