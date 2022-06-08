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
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {


    @FXML
    private Button signin;

    @FXML
    private Button logoutbtn;


    private Stage stage;
    private Scene scene;
    private Parent root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //TODO ; Schüler soll den am nächsten stehenden Prüfungstermin sehen können
    // und die entsprechende Note wenn die man an der Prüfung teilnimmt.
    // Dies könnte als eine Tabelle veranschaulicht werden.

    //TODO 2; Schüler soll den am nächsten stehende Fahrstunden sehen können,
    // mit dem Namen des Lehrers daneben.


    public void logoutButtonPressed(ActionEvent event) throws IOException {

        //Das ist für den Scene wechsel, kannst du überall benutzen aber ändere den Namen des fxml file
        //zu den du wechseln willst.
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) logoutbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

    public void siginButtonPressed(ActionEvent event) {

        //Ändere Seite auf Wählerseite und wähle von lehrer oder schüler aus
        //Füge dann diese
    }

}
