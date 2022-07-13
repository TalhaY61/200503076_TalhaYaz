package com.sample.fahrschuleapp;

public class DrivingLesson {

    private String drivingStudent;
    private String drivingInstructor;

    public DrivingLesson(String drivingStudent, String drivingInstructor, String date) {
        this.drivingInstructor = drivingInstructor;
        this.drivingStudent = drivingStudent;
    }

    public String getDrivingStudent() {
        return drivingStudent;
    }
    public void setDrivingStudent(String drivingStudent) {
        this.drivingStudent = drivingStudent;
    }
    public String getDrivingInstructor() {
        return drivingInstructor;
    }
    public void setDrivingInstructor(String drivingInstructor) {
        this.drivingInstructor = drivingInstructor;
    }


}
