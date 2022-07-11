package com.sample.fahrschuleapp;

public class UserSearchModel {

    String Role, Username;
    String FirstName, SurName;


    public UserSearchModel(String Role, String Username, String FirstName, String SurName) {
        this.Role = Role;
        this.Username = Username;
        this.FirstName = FirstName;
        this.SurName = SurName;
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
}
