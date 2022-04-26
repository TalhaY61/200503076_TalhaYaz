package com.sample.fahrschuleapp;

public class Fahrlehrer extends Person{

    private int gehalt;
    private String password;

    public Fahrlehrer(String name, String nachname, int alter, String geburtstag, String tlfnnummer, char geschlecht,
                      String ID, String email, int gehalt) {
        super(name, nachname, alter, geburtstag, tlfnnummer, geschlecht, ID, email);

        this.gehalt = gehalt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGehalt() {
        return gehalt;
    }
    public void setGehalt(int gehalt) {
        this.gehalt = gehalt;
    }
}
