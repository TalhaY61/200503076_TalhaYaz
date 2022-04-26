package com.sample.fahrschuleapp;

public class Fahrstunden {

    private String fahrschuler;
    private String fahrlehrer;


    public Fahrstunden(String fahrschuler, String fahrlehrer, String datum) {
        this.fahrlehrer = fahrlehrer;
        this.fahrschuler = fahrschuler;

    }

    public String getFahrschuler() {
        return fahrschuler;
    }

    public void setFahrschuler(String fahrschuler) {
        this.fahrschuler = fahrschuler;
    }

    public String getFahrlehrer() {
        return fahrlehrer;
    }

    public void setFahrlehrer(String fahrlehrer) {
        this.fahrlehrer = fahrlehrer;
    }


}
