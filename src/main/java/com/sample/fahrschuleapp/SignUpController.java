package com.sample.fahrschuleapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {


    @FXML
    private Button registerbtn;

    @FXML
    private Button cancelsigninbtn;

    @FXML
    private Label registertxtlabel;

    @FXML
    private RadioButton rb_male;

    @FXML
    private RadioButton rb_female;



    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rb_male.setToggleGroup(toggleGroup);
        rb_female.setToggleGroup(toggleGroup);

    }


    public void cancelSignInButtonPressed(ActionEvent event) throws IOException {

        //Das ist für den Scene wechsel, kannst du überall benutzen aber ändere den Namen des fxml file
        //zu den du wechseln willst.
        root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
        stage = (Stage) cancelsigninbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

    public void registerButtonOnAction(ActionEvent event) throws IOException {

        //wenn Daten erfolgreich eingetragen sind und
        //der benutzer sich registriert hat, zeige eine Erfolgsnachricht.
        //wenn nicht dann gebe eine ErrorMessage.
        if (1 == 2) {
            registertxtlabel.setText("Successfully");
        } else {
            registertxtlabel.setText("Error. Check your Data and Try Again");
        }
    }




}
