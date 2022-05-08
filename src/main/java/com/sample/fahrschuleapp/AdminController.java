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
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    @FXML
    private Button registerUserbtn; //für das registieren der studenten/lehrer
    @FXML
    private Button logoutbtn; //für das ausloggen
    @FXML
    private Button deleteUserButton; //für deleten der student/lehrer

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void logoutButtonPressed(ActionEvent event) throws IOException {
        //switch zur LogOut Seite zurück.
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) logoutbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }
    public void registerUserButtonPressed(ActionEvent event) throws IOException {
        //switche zur SignUp Seite und register die jeweilige Person.
        root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        stage = (Stage) logoutbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }
    public void deleteUserButtonOnAction(ActionEvent event) throws IOException {
        //switche zu delete seite
        root = FXMLLoader.load(getClass().getResource("deleteUserView.fxml"));
        stage = (Stage) logoutbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }




}
