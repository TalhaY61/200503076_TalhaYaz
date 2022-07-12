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
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {


    @FXML
    private Button registerbtn;
    @FXML
    private Button cancelsigninbtn;
    @FXML
    private Button deleteconfirmbtn;
    @FXML
    private Button saveButton, saveStudentButton, saveInstructorButton;
    @FXML
    private Label registertxtlabel, deletetxtlabel;
    @FXML
    private TextField nametxtfield;
    @FXML
    private TextField surnametxtfield;
    @FXML
    private TextField agetxtfield, pricetxtfield;
    @FXML
    private TextField emailtxtfield, drivingLicencetxtfield;
    @FXML
    private TextField phonenumbertxtfield, usernametxtfield, gendertxtfield, salarytxtfield, infoInstructortxtfield;
    @FXML
    private PasswordField passwordtxtfield;

    private Stage stage;
    private Scene scene;
    private Parent root;

    DatabaseConnection dc = new DatabaseConnection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void cancelSignInButtonPressed(ActionEvent event) throws IOException {
        //switch zur AdminView und dort kann man weiter machen.
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminView.fxml")));
        stage = (Stage) cancelsigninbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

    public void registerButtonInstructorPressed(ActionEvent event) throws IOException {
        registerInstructor();
    }
    public void registerButtonStudentPressed(ActionEvent event) throws IOException {
        registerStudent();
    }

    public void registerInstructor(){
        //TODO Das Gehalt muss mehr als 4200TRY sein kontrolliere diesen.
        //Verknüpft die MySQL und hier wird dann der USER REGISTRIERT.
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = nametxtfield.getText();
        String surname = surnametxtfield.getText();
        String age = agetxtfield.getText();
        String email = emailtxtfield.getText();
        String phonenumber = phonenumbertxtfield.getText();
        String username = usernametxtfield.getText();
        String password = passwordtxtfield.getText();
        String gender = gendertxtfield.getText();
        String salary = salarytxtfield.getText();

        String registered = dc.registerInstructor(firstname, surname, age, email, phonenumber, gender, salary, username, password);

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(registered);
            registertxtlabel.setText("Instructor has been registered!");
            //After Registered change scene to AdminView.
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminView.fxml")));
            stage = (Stage) registerbtn.getScene().getWindow();
            stage.setScene(new Scene(root, 520, 400));
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void registerStudent(){
        //Verknüpft die MySQL und hier wird dann der USER REGISTRIERT.
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = nametxtfield.getText();
        String surname = surnametxtfield.getText();
        String age = agetxtfield.getText();
        String email = emailtxtfield.getText();
        String phonenumber = phonenumbertxtfield.getText();
        String username = usernametxtfield.getText();
        String password = passwordtxtfield.getText();
        String gender = gendertxtfield.getText();
        String price = pricetxtfield.getText();
        String drivingLicence = drivingLicencetxtfield.getText();
        String InfoInstructor = infoInstructortxtfield.getText();


        //Die Befehle wurden in der "DatabaseConnection" Klasse hinzugefügt.
        String registered = dc.registerStudent(firstname, surname, age, email, phonenumber, gender, price, drivingLicence, username, password, InfoInstructor);
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(registered);
            registertxtlabel.setText("Student has been registered!");

            //After Registered change scene to AdminView.
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminView.fxml")));
            stage = (Stage) registerbtn.getScene().getWindow();
            stage.setScene(new Scene(root, 520, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    void setTextField(String firstName, String surName, String email, String age,
                      String phonenumber, String username, String password) {


        nametxtfield.setText(firstName);
        surnametxtfield.setText(surName);
        emailtxtfield.setText(email);
        agetxtfield.setText(age);
        phonenumbertxtfield.setText(phonenumber);
        usernametxtfield.setText(username);
        passwordtxtfield.setText(password);

    }

    public void saveUpdatedStudentButtonPressed(ActionEvent event) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = nametxtfield.getText();
        String surname = surnametxtfield.getText();
        String age = agetxtfield.getText();
        String email = emailtxtfield.getText();
        String phonenumber = phonenumbertxtfield.getText();
        String username = usernametxtfield.getText();
        String password = passwordtxtfield.getText();

        //Die Befehle wurden in der "DatabaseConnection" Klasse hinzugefügt.
        String updated = dc.updateStudent(firstname, surname, age, email, phonenumber, username, password);
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updated);
            registertxtlabel.setText("Student has been updated!");

            /*After Registered change scene to AdminView.
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminView.fxml")));
            stage = (Stage) registerbtn.getScene().getWindow();
            stage.setScene(new Scene(root, 520, 400));
            stage.show(); */
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void saveUpdatedInstructorButtonPressed(ActionEvent event) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = nametxtfield.getText();
        String surname = surnametxtfield.getText();
        String age = agetxtfield.getText();
        String email = emailtxtfield.getText();
        String phonenumber = phonenumbertxtfield.getText();
        String username = usernametxtfield.getText();
        String password = passwordtxtfield.getText();

        //Die Befehle wurden in der "DatabaseConnection" Klasse hinzugefügt.
        String updated = dc.updateInstructor(firstname, surname, age, email, phonenumber, username, password);
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updated);
            registertxtlabel.setText("Instructor has been updated!");

            /*After Registered change scene to AdminView.
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminView.fxml")));
            stage = (Stage) registerbtn.getScene().getWindow();
            stage.setScene(new Scene(root, 520, 400));
            stage.show(); */
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
