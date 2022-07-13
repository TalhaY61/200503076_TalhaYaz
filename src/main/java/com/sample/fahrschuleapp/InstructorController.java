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
    private Button logoutbtn;
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
    @FXML
    private TableColumn<UserSearchModel, String> ageColumn;
    @FXML
    private TableColumn<UserSearchModel, String> emailColumn;
    @FXML
    private TableColumn<UserSearchModel, String> drivingLessonColumn;

    ObservableList<UserSearchModel> studentSearchModel = FXCollections.observableArrayList();

    UserSearchModel user;
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    private Stage stage;
    private Parent root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Get Student information for the TableView in the InstructorView
        //SQL Query - Execute in the backend Database
        String studentViewQuery = connectNow.getStudentData();
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(studentViewQuery);

            while(queryOutput.next()) {
                String queryFirstName = queryOutput.getString("FirstName");
                String querySurName = queryOutput.getString("SurName");
                String queryAge = queryOutput.getString("Age");
                String queryEmail = queryOutput.getString("Email");
                String queryDrivingLicence = queryOutput.getString("DrivingLicenceType");
                String queryDrivingLesson = queryOutput.getString("DrivingLesson");

                //Populate the ObservableList
                studentSearchModel.add(new UserSearchModel(queryFirstName, querySurName, queryAge, queryEmail, queryDrivingLicence, queryDrivingLesson));
            }

            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
            surNameColumn.setCellValueFactory(new PropertyValueFactory<>("SurName"));
            ageColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
            drivingLicenceColumn.setCellValueFactory(new PropertyValueFactory<>("DrivingLicenceType"));
            drivingLessonColumn.setCellValueFactory(new PropertyValueFactory<>("DrivingLesson"));

            studentTableView.setItems(studentSearchModel);
            studentTableView.refresh();

        } catch (SQLException e) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void logoutButtonPressed(ActionEvent event) throws IOException {
        //Logout Button. Switch to the Login Page
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage = (Stage) logoutbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

}
