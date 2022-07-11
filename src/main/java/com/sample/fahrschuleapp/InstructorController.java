package com.sample.fahrschuleapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InstructorController implements Initializable {

    @FXML
    private Button applyButton;
    @FXML
    private Button logoutbtn;
    @FXML
    private TextField firstnametxtfield, surnametxtfield, agetxtfield, emailtxtfield;
    @FXML
    private TextField phonenumbertxtfield, gendertxtfield, usernametxtfield, passwordtxtfield;
    @FXML
    private Label messageLabel;

    @FXML
    private TableView<UserSearchModel> studentTableView;
    @FXML
    private TableColumn<UserSearchModel, String> firstNameColumn;
    @FXML
    private TableColumn<UserSearchModel, String> surNameColumn;
    @FXML
    private TableColumn<UserSearchModel, String> drivingLicenceColumn;

    ObservableList<UserSearchModel> studentSearchModel = FXCollections.observableArrayList();

    DatabaseConnection connectNow = new DatabaseConnection();

    private Stage stage;
    private Scene scene;
    private Parent root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Connection connectDB = connectNow.getConnection();

        //SQL Query - Execute in the backend Database
        String userViewQuery = "SELECT FirstName, SurName, DrivingLicenceType FROM student";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(userViewQuery);

            while(queryOutput.next()) {

                String queryFirstName = queryOutput.getString("FirstName");
                String querySurName = queryOutput.getString("SurName");
                String queryDrivingLicence = queryOutput.getString("DrivingLicenceType");

                //Populate the ObservableList
                studentSearchModel.add(new UserSearchModel(queryFirstName, querySurName, queryDrivingLicence));
            }

            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
            surNameColumn.setCellValueFactory(new PropertyValueFactory<>("SurName"));
            drivingLicenceColumn.setCellValueFactory(new PropertyValueFactory<>("DrivingLicenceType"));

            studentTableView.setItems(studentSearchModel);
            studentTableView.refresh();

        } catch (SQLException e) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void logoutButtonPressed(ActionEvent event) throws IOException {

        //Das ist für den Scene wechsel, kannst du überall benutzen aber ändere den Namen des fxml file,
        // zu den du wechseln willst.
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage = (Stage) logoutbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

    public void applyButtonPressed(ActionEvent event) throws IOException {
        Connection connectDB = connectNow.getConnection();

        String firstname = firstnametxtfield.getText();
        String surname = surnametxtfield.getText();
        String age = agetxtfield.getText();
        String email = emailtxtfield.getText();
        String phonenumber = phonenumbertxtfield.getText();
        String gender = gendertxtfield.getText();
        String username = usernametxtfield.getText();
        String password = passwordtxtfield.getText();

        String updated = connectNow.updateInstructor(firstname, surname, age, email, phonenumber, gender, username, password);
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updated);
            messageLabel.setText("Updated Information successfully");
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            messageLabel.setText("Update Error");
        }
    }

    void setTextField(String firstName, String surName, String email, String age,
                      String phonenumber, String username, String password) {


        firstnametxtfield.setText(firstName);
        surnametxtfield.setText(surName);
        emailtxtfield.setText(email);
        agetxtfield.setText(age);
        phonenumbertxtfield.setText(phonenumber);
        usernametxtfield.setText(username);
        passwordtxtfield.setText(password);

    }


}
