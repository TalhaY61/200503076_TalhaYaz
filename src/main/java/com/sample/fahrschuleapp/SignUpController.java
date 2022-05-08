package com.sample.fahrschuleapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {


    @FXML
    private Button registerbtn;
    @FXML
    private Button cancelsigninbtn;
    @FXML
    private Button deleteconfirmbtn;
    @FXML
    private Label registertxtlabel, deletetxtlabel;
    @FXML
    public TextField roletxtfield;
    @FXML
    private TextField nametxtfield;
    @FXML
    private TextField surnametxtfield;
    @FXML
    private TextField agetxtfield;
    @FXML
    private TextField emailtxtfield;
    @FXML
    private TextField phonenumbertxtfield, usernametxtfield, gendertxtfield;
    @FXML
    private PasswordField passwordtxtfield;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void cancelSignInButtonPressed(ActionEvent event) throws IOException {
        //switch zur AdminView und dort kann man weiter machen.
        root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
        stage = (Stage) cancelsigninbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

    public void registerButtonOnAction(ActionEvent event) throws IOException {

        //wenn Daten erfolgreich eingetragen sind und
        //der benutzer sich registriert hat, zeige eine Erfolgsnachricht.
        //wenn nicht dann gebe eine ErrorMessage.
        registerUser();
    }
    public void deleteButtonOnAction(ActionEvent event) throws IOException {
        deleteUser();
    }

    public void registerUser(){
        //Verknüpft die MySQL und hier wird dann der USER REGISTRIERT.
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String role = roletxtfield.getText();
        String firstname = nametxtfield.getText();
        String surname = surnametxtfield.getText();
        String age = agetxtfield.getText();
        String email = emailtxtfield.getText();
        String phonenumber = phonenumbertxtfield.getText();
        String username = usernametxtfield.getText();
        String password = passwordtxtfield.getText();
        String gender = gendertxtfield.getText();

        String insertFields = "INSERT INTO useraccounts (Role, FirstName, SurName, Age, Email, Phonenumber, Gender , Username, Password) VALUES ('";
        String insertValues = role + "','" + firstname + "','" + surname  + "','" + age  + "','" + email  + "','" + phonenumber  + "','" + gender  + "','" + username  + "','" + password + "');";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            registertxtlabel.setText("User has been registered!");

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void deleteUser() {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String username = usernametxtfield.getText();
        String password = passwordtxtfield.getText();

        String deleteUser = "DELETE FROM useraccounts WHERE Username=" + "'" + username + "';";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(deleteUser);
            deletetxtlabel.setText("User has been removed!");

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }




}
