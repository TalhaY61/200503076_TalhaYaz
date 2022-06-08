package com.sample.fahrschuleapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    @FXML
    private Button registerInstructor, registerStudent; //für das registieren der studenten/lehrer
    @FXML
    private Button logoutbtn; //für das ausloggen
    @FXML
    private Button deleteUserBtn; //für deleten der student/lehrer
    @FXML
    private Button addDrivingLessonBtn, viewDrivingLessonBtn;


    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void logoutButtonPressed(ActionEvent event) throws IOException {
        //switch zur LogOut Seite zurück.
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage = (Stage) logoutbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }
    public void registerInstructorButtonPressed(ActionEvent event) throws IOException {
        //switche zur SignUp Seite und register die jeweilige Person.
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InstructorSignUpPage.fxml")));
        stage = (Stage) registerInstructor.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }
    public void registerStudentButtonPressed(ActionEvent event) throws IOException {
        //switche zur SignUp Seite und register die jeweilige Person.
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentSignUpPage.fxml")));
        //getClass().getResource("StudentSignUpPage.fxml") might be null sagt er
        stage = (Stage) registerStudent.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

    //delete button kann man verknüpfen denke ich
    public void deleteUserButtonPressed(ActionEvent event) throws IOException {
        //switche zu delete seite
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("deleteUserView.fxml")));
        stage = (Stage) deleteUserBtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

    public void viewDrivingLessonButtonPressed(ActionEvent event) {
        //Das ansehen der Fahrstunden der Schüler in einer Art Kalender vlt
    }


    public void addDrivingLessonButtonPressed (ActionEvent event) {
        //Das hinzufügen von Fahrstunden für die Schüler, für neu
        // angemeldete und bevorstehende.
    }


}
