package com.sample.fahrschuleapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {


    @FXML
    private Button signinbtn;

    @FXML
    private Button cancelsigninbtn;

    @FXML
    private Label registertxtlabel;


    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


    public void cancelSignInButtonPressed(ActionEvent event) throws IOException {

        //Das ist für den Scene wechsel, kannst du überall benutzen aber ändere den Namen des fxml file
        //zu den du wechseln willst.
        root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
        stage = (Stage) cancelsigninbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

    public void siginButtonPressed(ActionEvent event) throws IOException {

        //Ändere Seite auf Wählerseite und wähle von lehrer oder schüler aus
        //Füge dann diese
        root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        stage = (Stage) signinbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }




}
