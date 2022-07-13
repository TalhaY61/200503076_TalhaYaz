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
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
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
    private Button updateStudent, updateInstructor;
    @FXML
    private Button refreshbtn, addDrivingLButton;
    @FXML
    private Label alertMessageLabel;

    //TableView im AdminView Pannel, welches Schüler und Lehrer anzeigt
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
    private TableColumn<UserSearchModel, String> ageColumn;
    @FXML
    private TableColumn<UserSearchModel, String> emailColumn;
    @FXML
    private TableColumn<UserSearchModel, String> phonenumberColumn;
    @FXML
    private TableColumn<UserSearchModel, String> passwordColumn;

    @FXML
    private TextField searchTextField;

    ObservableList<UserSearchModel> userSearchObserverList = FXCollections.observableArrayList();
    UserSearchModel user = null;


    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    private Stage stage;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String userViewQuery = connectNow.getUserData();
        //TableView in AdminView from Student and Instructor
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(userViewQuery);

            while(queryOutput.next()) {

                String queryRole = queryOutput.getString("Role");
                String queryFirstName = queryOutput.getString("FirstName");
                String querySurName = queryOutput.getString("SurName");
                String queryAge = queryOutput.getString("Age");
                String queryEmail = queryOutput.getString("Email");
                String queryPhonenumber = queryOutput.getString("Phonenumber");
                String queryUsername = queryOutput.getString("Username");
                String queryPassword = queryOutput.getString("Password");
                //Populate the ObservableList
                userSearchObserverList.add(new UserSearchModel(queryRole, queryFirstName, querySurName, queryAge, queryEmail,
                        queryPhonenumber, queryUsername, queryPassword));
            }

            roleColumn.setCellValueFactory(new PropertyValueFactory<>("Role"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
            surnameColumn.setCellValueFactory(new PropertyValueFactory<>("SurName"));
            ageColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
            phonenumberColumn.setCellValueFactory(new PropertyValueFactory<>("Phonenumber"));
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));
            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));

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
                    else if (userSearchModel.getFirstName().toLowerCase().indexOf(searchKeyword) > -1)
                        return true; //means no found a match in FirstName
                    else if (userSearchModel.getSurName().toLowerCase().indexOf(searchKeyword) > -1)
                        return true;
                    else if (userSearchModel.getAge().toLowerCase().indexOf(searchKeyword) > -1)
                        return true;
                    else if (userSearchModel.getEmail().toLowerCase().indexOf(searchKeyword) > -1)
                        return true;
                    else if (userSearchModel.getPhonenumber().toLowerCase().indexOf(searchKeyword) > -1)
                        return true;
                    else if (userSearchModel.getUsername().toLowerCase().indexOf(searchKeyword) > -1)
                        return true; //means no found a match in Username
                    else if (userSearchModel.getPassword().toLowerCase().indexOf(searchKeyword) > -1)
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
        //switch to the Login-Page
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage = (Stage) logoutbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }
    public void registerInstructorButtonPressed(ActionEvent event) throws IOException {
        //Switch to the InstructorSignUpPage and register the Instructor.
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InstructorSignUpPage.fxml")));
        stage = (Stage) registerInstructor.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }
    public void registerStudentButtonPressed(ActionEvent event) throws IOException {
        //Switch to the StudentSignUpPage and register the Student.
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentSignUpPage.fxml")));
        stage = (Stage) registerStudent.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

    public void deleteUserButtonPressed(ActionEvent event) throws IOException {
        //Delete the selected User.
        if (userTabelView.getSelectionModel().getSelectedItem().getRole().equals("Student")) {

            String username = userTabelView.getSelectionModel().getSelectedItem().getUsername();
            String deleteUser = connectNow.deleteStudent(username);
            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(deleteUser);

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

        } else if (userTabelView.getSelectionModel().getSelectedItem().getRole().equals("Instructor")) {

            String username = userTabelView.getSelectionModel().getSelectedItem().getUsername();
            String deleteUser = connectNow.deleteInstructor(username);
            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(deleteUser);

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }else {
            alertMessageLabel.setText("Error! Could not delete the selected User. Try Again!");
        }

    }

    public void refreshButtonPressed(ActionEvent event) throws IOException {
        //Load AdminView again to refresh the TableView
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminView.fxml")));
        stage = (Stage) refreshbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

    public void updateInstructorButtonPressed() throws IOException {

        //IF the selected Row has the Role Column equal to "Instructor", switch to UpdateInstructorPage
        // and update the information from the Student
        user = userTabelView.getSelectionModel().getSelectedItem();
        String control = userTabelView.getSelectionModel().getSelectedItem().getRole();

        if (control.equals("Student")) alertMessageLabel.setText("Wrong Button for the selected User");
        else {
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("UpdateInstructorPage.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

            SignUpController signUpController = loader.getController();
            signUpController.setTextField(user.getFirstName(), user.getSurName(),
                    user.getEmail(),user.getAge(), user.getPhonenumber(),
                    user.getUsername(), user.getPassword());

            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }
    }


    public void updateStudentButtonPressed() throws IOException {

        //IF the selected Row has the Role Column equal to "Student", switch to UpdateStudentPage
        // and update the information from the Student
        user = userTabelView.getSelectionModel().getSelectedItem();
        String control = userTabelView.getSelectionModel().getSelectedItem().getRole();

        if (control.equals("Instructor")) alertMessageLabel.setText("Wrong Button for the selected User");
        else {
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("UpdateStudentPage.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

            SignUpController signUpController = loader.getController();
            signUpController.setTextField(user.getFirstName(), user.getSurName(),
                    user.getEmail(),user.getAge(), user.getPhonenumber(),
                    user.getUsername(), user.getPassword());

            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }
    }

    public void addDrivingLessonButtonPressed (ActionEvent event) {

        //get the information from the Student-Columns and open the AddDrivingLessonPage,
        //here you can add Driving Lessons by hour to the student.
        user = userTabelView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource("AddDrivingLessonPage.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        SignUpController signUpController = loader.getController();
        signUpController.setTextField(user.getFirstName(), user.getSurName(),
                user.getEmail(),user.getAge(), user.getPhonenumber(),
                user.getUsername(), user.getPassword());

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
}
