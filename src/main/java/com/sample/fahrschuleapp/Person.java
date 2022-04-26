package com.sample.fahrschuleapp;


public class Person {

    private String name;
    private String nachname;
    private int alter;
    private String geburtstag;
    private String tlfnnummer;
    private char geschlecht;
    private String ID;
    public String email;

    public Person(String name, String nachname, int alter, String geburtstag, String tlfnnummer, char geschlecht, String ID, String email) {
        this.name = name;
        this.nachname = nachname;
        this.alter = alter;
        this.geburtstag = geburtstag;
        this.tlfnnummer = tlfnnummer;
        this.geschlecht = geschlecht;
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

    public char getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(char geschlecht) {
        this.geschlecht = geschlecht;
    }

    public String getTlfnnummer() {
        return tlfnnummer;
    }

    public void setTlfnnummer(String tlfnnummer) {
        this.tlfnnummer = tlfnnummer;
    }

    public String getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(String geburtstag) {
        this.geburtstag = geburtstag;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

