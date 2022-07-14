package com.sample.fahrschuleapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {


    //Register Student- Instructor Page
    @FXML
    private Button registerbtn;
    @FXML
    private Button cancelsigninbtn;
    @FXML
    private Button saveStudentButton, saveInstructorButton;
    @FXML
    private Label registertxtlabel;
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

    //Add Driving Lesson Page
    @FXML
    private TextField currentDLtxtfield, newDLtextfield;
    @FXML
    private Button saveDrivingLessonButton;

    private Stage stage;
    private Scene scene;
    private Parent root;


    DatabaseConnection dc = new DatabaseConnection();
    Connection connectDB = dc.getConnection();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void cancelSignInButtonPressed(ActionEvent event) throws IOException {
        //Swtich to AdminView and continue whatever you want to do.
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminView.fxml")));
        stage = (Stage) cancelsigninbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

    //Register Instructor and Student
    public void registerButtonInstructorPressed(ActionEvent event) throws IOException {
        registerInstructor();
    }
    public void registerButtonStudentPressed(ActionEvent event) throws IOException {
        registerStudent();
    }

    public void registerInstructor() throws IOException {
        //Register the Instructor and connect it with the SQL Database
        String firstname = nametxtfield.getText();
        String surname = surnametxtfield.getText();
        String age = agetxtfield.getText();
        String email = emailtxtfield.getText();
        String phonenumber = phonenumbertxtfield.getText();
        String username = usernametxtfield.getText();
        String password = passwordtxtfield.getText();
        String gender = gendertxtfield.getText();
        String salary = salarytxtfield.getText();
        int minGehalt = Integer.parseInt(salary);

        if (minGehalt <= 4500) registertxtlabel.setText("Gehalt muss mehr als 4500 sein!");
        else {
            dc.registerInstructor(firstname, surname, age, email, phonenumber, gender, salary, username, password);
            //After Registered change scene to AdminView.
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminView.fxml")));
            stage = (Stage) registerbtn.getScene().getWindow();
            stage.setScene(new Scene(root, 520, 400));
            stage.show();
        }

    }
    public void registerStudent() throws IOException {
        //Register the Student and connect it with the SQL Database

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

        if(drivingLicence.equals("A1") || drivingLicence.equals("A2") || drivingLicence.equals("B") || drivingLicence.equals("B1")) {
            dc.registerStudent(firstname, surname, age, email, phonenumber, gender, price, drivingLicence, username, password, InfoInstructor);
            //After Registered change scene to AdminView.
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminView.fxml")));
            stage = (Stage) registerbtn.getScene().getWindow();
            stage.setScene(new Scene(root, 520, 400));
            stage.show();
        } else {
            registertxtlabel.setText("Wrong Driving Licence Type entered!");
        }
    }

    //Update Student and Instructor Page
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

    public void saveUpdatedStudentButtonPressed(ActionEvent event) throws IOException {

        String firstname = nametxtfield.getText();
        String surname = surnametxtfield.getText();
        String age = agetxtfield.getText();
        String email = emailtxtfield.getText();
        String phonenumber = phonenumbertxtfield.getText();
        String username = usernametxtfield.getText();
        String password = passwordtxtfield.getText();

        //Die Befehle wurden in der "DatabaseConnection" Klasse hinzugefügt.
        dc.updateStudent(firstname, surname, age, email, phonenumber, username, password);
        registertxtlabel.setTextFill(Color.GREEN);
        registertxtlabel.setText("User Updated successfully");

    }

    public void saveUpdatedInstructorButtonPressed(ActionEvent event) throws IOException {

        String firstname = nametxtfield.getText();
        String surname = surnametxtfield.getText();
        String age = agetxtfield.getText();
        String email = emailtxtfield.getText();
        String phonenumber = phonenumbertxtfield.getText();
        String username = usernametxtfield.getText();
        String password = passwordtxtfield.getText();

        dc.updateInstructor(firstname, surname, age, email, phonenumber, username, password);
        registertxtlabel.setTextFill(Color.GREEN);
        registertxtlabel.setText("User Updated successfully");
    }

    public void saveUpdatedDrivingLessonButtonPressed(ActionEvent event) throws IOException {

        String username = usernametxtfield.getText();
        String newDrivingL = newDLtextfield.getText();

        dc.updateCurrentDrivingLesson(newDrivingL, username);
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminView.fxml")));
        stage = (Stage) saveDrivingLessonButton.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();

    }
}
