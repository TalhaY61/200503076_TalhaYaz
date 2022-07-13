package com.sample.fahrschuleapp;

import javafx.scene.control.PasswordField;

public class UserSearchModel {

    String Role, Username, Password;
    String FirstName, SurName, Age, Email, Phonenumber;
    String DrivingLicenceType;
    String DrivingLesson;

    //For the TableViews on the InstructorView Page and AdminView Page.

    public UserSearchModel(String Role, String FirstName, String SurName,String Age, String Email, String Phonenumber, String Username,
                           String Password) {
        this.Role = Role;
        this.Username = Username;
        this.FirstName = FirstName;
        this.SurName = SurName;
        this.Age = Age;
        this.Email = Email;
        this.Phonenumber = Phonenumber;
        this.Password = Password;
    }

    public UserSearchModel(String FirstName, String SurName,String Age, String Email, String DrivingLicenceType,  String DrivingLesson) {
        this.FirstName = FirstName;
        this.SurName = SurName;
        this.Age = Age;
        this.Email = Email;
        this.DrivingLicenceType = DrivingLicenceType;
        this.DrivingLesson = DrivingLesson;
    }


    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getSurName() {
        return SurName;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public void setFirstName(String FirstName) {
        FirstName = FirstName;
    }

    public void setSurName(String SurName) {
        SurName = SurName;
    }

    public String getDrivingLicenceType() { return DrivingLicenceType;}

    public void setDrivingLicenceType(String DrivingLicenceType) {this.DrivingLicenceType = DrivingLicenceType;}

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }

    public String getDrivingLesson() {
        return DrivingLesson;
    }

    public void setDrivingLesson(String drivingLesson) {
        DrivingLesson = drivingLesson;
    }
}
