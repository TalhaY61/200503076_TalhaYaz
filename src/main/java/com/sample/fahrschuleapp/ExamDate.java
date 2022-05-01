package com.sample.fahrschuleapp;

public class ExamDate {

    private String date;
    private String examstart; //Prüfungsstart
    private String examend; //Prüfungsende
    private String examroom; //Prüfungsraum


    public ExamDate(String date, String examstart, String examend, String examroom) {
        this.date = date;
        this.examstart = examstart;
        this.examend = examend;
        this.examroom = examroom;
    }


    public String getExamRoom() {
        return examroom;
    }


    public void setExamRoom(String examroom) {
        this.examroom = examroom;
    }


    public String getExamEnd() {
        return examend;
    }


    public void setExamEnd(String examend) {
        this.examend = examend;
    }


    public String getExamStart() {
        return examstart;
    }


    public void setExamStart(String examstart) {
        this.examstart = examstart;
    }


    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }


}
