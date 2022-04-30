package com.sample.fahrschuleapp;

public class Fahrschueler extends Person{

    private char fuhrerscheinart;
    private String infolehrer;
    private int preis;

    public Fahrschueler(String name, String nachname, int alter, String geburtstag, String tlfnnummer, char geschlecht,
                       String ID, String email,char fuhrerscheinart, String infolehrer, int preis) {
        super(name, nachname, alter, geburtstag, tlfnnummer, geschlecht, ID, email);

        this.preis = preis;
        this.fuhrerscheinart = fuhrerscheinart;
        this.infolehrer = infolehrer;
    }


    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public String getInfolehrer() {
        return infolehrer;
    }

    public void setInfolehrer(String infolehrer) {
        this.infolehrer = infolehrer;
    }


    public char getFuhrerscheinArt() {
        return fuhrerscheinart;
    }

    public void setFuhrerscheinArt(char fuhrerschein) {
        this.fuhrerscheinart = fuhrerschein;
    }

}
