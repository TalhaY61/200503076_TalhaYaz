package com.sample.fahrschuleapp;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private TableView<UserSearchModel> userTabelView;
    @FXML
    private TableColumn<UserSearchModel, String> roleColumn;
    @FXML
    private TableColumn<UserSearchModel, String> surnameColumn;
    @FXML
    private TableColumn<UserSearchModel, String> firstNameColumn;
    @FXML
    private TextField searchTextField;

    ObservableList<UserSearchModel> userSearchObserverList = FXCollections.observableArrayList();


    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        //SQL Query - Execute in the backend Database
        String userViewQuery = "SELECT Role, FirstName, SurName FROM instructor\n" +
                "UNION\n" +
                "SELECT Role, FirstName, SurName FROM student";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(userViewQuery);

            while(queryOutput.next()) {

                String queryID = queryOutput.getString("Role");
                String queryFirstName = queryOutput.getString("FirstName");
                String querySurName = queryOutput.getString("SurName");

                //Populate the ObservableList
                userSearchObserverList.add(new UserSearchModel(queryID, queryFirstName, querySurName));
            }

            roleColumn.setCellValueFactory(new PropertyValueFactory<>("Role"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
            surnameColumn.setCellValueFactory(new PropertyValueFactory<>("SurName"));

            userTabelView.setItems(userSearchObserverList);

            /*
            //Initila filtered List
            FilteredList<UserSearchModel> filteredData = new FilteredList<>(userSearchObserverList, b -> true);

            searchTextField.textProperty().addListener((observable -> oldValue, newValue) -> {
                filteredData.setPredicate(userSearchModel -> {
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchkeyword = newValue.toLowerCase();

                    if(UserSearchModel.getID().toLowerCase().indexOf(searchTextField)>-1) {
                        return true;// Means we foundamatch in ProductName
                    }else if(searchTextField.getFirstName().toLowerCase().indexOf(searchKeyword)
                        return true;// Means we foundamatch in Description
                    }else if(UserSearchModel.getSurName().toLowerCase().indexOf(search searchTextField)>-1){
                        return true;
                    }else if(product SearchModel.getModel Number().toLowerCase().indexOf(searchKeyword)>-1){
                        searchTextField
                    }else if(product SearchModel.getModel Year().toString().indexOf(searchkeyword)>-1){
                        return true;
                    }else
                    return false;// no match found
                });
            });
            */

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
