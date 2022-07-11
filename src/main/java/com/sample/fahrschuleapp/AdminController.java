package com.sample.fahrschuleapp;

import javafx.beans.Observable;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminController implements Initializable {


    @FXML
    private Button registerInstructor, registerStudent; //für das registieren der studenten/lehrer
    @FXML
    private Button logoutbtn; //für das ausloggen
    @FXML
    private Button deleteUserBtn; //für deleten der student/lehrer
    @FXML
    private Button addDrivingLessonBtn, viewDrivingLessonBtn;
    @FXML
    private Button refreshbtn;
    @FXML
    private Label messageLabel;

    @FXML
    private TableView<UserSearchModel> userTabelView;
    @FXML
    private TableColumn<UserSearchModel, String> roleColumn;
    @FXML
    private TableColumn<UserSearchModel, String> usernameColumn;
    @FXML
    private TableColumn<UserSearchModel, String> surnameColumn;
    @FXML
    private TableColumn<UserSearchModel, String> firstNameColumn;
    @FXML
    private TextField searchTextField;

    ObservableList<UserSearchModel> userSearchObserverList = FXCollections.observableArrayList();

    DatabaseConnection connectNow = new DatabaseConnection();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Connection connectDB = connectNow.getConnection();

        //SQL Query - Execute in the backend Database
        String userViewQuery = "SELECT Role, Username, FirstName, SurName FROM instructor\n" +
                "UNION\n" +
                "SELECT Role, Username, FirstName, SurName FROM student";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(userViewQuery);

            while(queryOutput.next()) {

                String queryRole = queryOutput.getString("Role");
                String queryUsername = queryOutput.getString("Username");
                String queryFirstName = queryOutput.getString("FirstName");
                String querySurName = queryOutput.getString("SurName");

                //Populate the ObservableList
                userSearchObserverList.add(new UserSearchModel(queryRole, queryUsername, queryFirstName, querySurName));
            }

            roleColumn.setCellValueFactory(new PropertyValueFactory<>("Role"));
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
            surnameColumn.setCellValueFactory(new PropertyValueFactory<>("SurName"));

            userTabelView.setItems(userSearchObserverList);
            userTabelView.refresh();


            //Initila filtered List
            FilteredList<UserSearchModel> filteredData = new FilteredList<>(userSearchObserverList, b -> true);

            searchTextField.textProperty().addListener((observable,oldValue, newValue) ->  {
                filteredData.setPredicate(userSearchModel -> {

                    //If no match value then display all record or whatever records it current have, no changes
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (userSearchModel.getRole().toLowerCase().indexOf(searchKeyword) > -1)
                        return true; //means no found a match in Role
                    else if (userSearchModel.getUsername().toLowerCase().indexOf(searchKeyword) > -1)
                        return true; //means no found a match in Username
                    else if (userSearchModel.getFirstName().toLowerCase().indexOf(searchKeyword) > -1)
                        return true; //means no found a match in FirstName
                    else if (userSearchModel.getSurName().toLowerCase().indexOf(searchKeyword) > -1)
                        return true;
                    else
                        return false; //no match found.
                });
            });

            //Bind sorted result with table view
            SortedList<UserSearchModel> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(userTabelView.comparatorProperty());

            //Appy filtered and sorted data to the table view
            userTabelView.setItems(sortedData);

        } catch (SQLException e) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
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
        //delete den ausgewählten user

        if (userTabelView.getSelectionModel().getSelectedItem().getRole().equals("Student")) {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            String name = userTabelView.getSelectionModel().getSelectedItem().getFirstName();


            String deleteUser = "DELETE FROM Student WHERE FirstName = '" + name + "';";
            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(deleteUser);

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

        } else if (userTabelView.getSelectionModel().getSelectedItem().getRole().equals("Instructor")) {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            String name = userTabelView.getSelectionModel().getSelectedItem().getFirstName();

            String deleteUser = "DELETE FROM Instructor WHERE FirstName = '" + name + "';";
            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(deleteUser);

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }else {
            messageLabel.setText("Error! Could not delete the selected User. Try Again!");
        }

    }

    public void refreshButtonPressed(ActionEvent event) throws IOException {
        //Statt die refresh funktion zu benutzen, loade ich einfach die AdminView Szene von neu!
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminView.fxml")));
        stage = (Stage) refreshbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

    public void updateInstructorButtonPressed() {

    }

    public void updateStudentButtonPressed() {

    }
    public void viewDrivingLessonButtonPressed(ActionEvent event) {
        //Das ansehen der Fahrstunden der Schüler in einer Art Kalender vlt
    }

    public void addDrivingLessonButtonPressed (ActionEvent event) {
        //Das hinzufügen von Fahrstunden für die Schüler, für neu
        // angemeldete und bevorstehende.
    }



}
