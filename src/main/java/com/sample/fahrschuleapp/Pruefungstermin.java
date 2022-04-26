package com.sample.fahrschuleapp;

public class Pruefungstermin {

    private String datum;
    private String pstart; //Prüfungsstart
    private String pende; //Prüfungsende
    private String praum; //Prüfungsraum


    public Pruefungstermin(String datum, String pstart, String pende, String praum) {
        this.datum = datum;
        this.pstart = pstart;
        this.pende = pende;
        this.praum = praum;
    }


    public String getPraum() {
        return praum;
    }


    public void setPraum(String praum) {
        this.praum = praum;
    }


    public String getPende() {
        return pende;
    }


    public void setPende(String pende) {
        this.pende = pende;
    }


    public String getPstart() {
        return pstart;
    }


    public void setPstart(String pstart) {
        this.pstart = pstart;
    }


    public String getDatum() {
        return datum;
    }


    public void setDatum(String datum) {
        this.datum = datum;
    }


}
