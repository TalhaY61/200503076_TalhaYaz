package com.sample.fahrschuleapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import com.sample.fahrschuleapp.SignUpController;

import javax.xml.transform.Result;


public class LogInController implements Initializable {
    @FXML
    private Button loginbtn;

    @FXML
    private Button cancelbtn;

    @FXML
    private Label loginmissinglabel;

    @FXML
    private TextField usernametxtfield;

    @FXML
    private PasswordField passwordtxtfield;

    private Stage stage;
    private Scene scene;
    private Parent root;

    SignUpController signup = new SignUpController();
    DatabaseConnection dc = new DatabaseConnection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loginButtonOnAction(ActionEvent event) throws IOException {

        if ((usernametxtfield.getText().isBlank() == false) && passwordtxtfield.getText().isBlank() == false) {
            validateLogin();
        } else {
            loginmissinglabel.setText("Please Enter your Username or Password!");
        }
    }

    @FXML
    protected void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelbtn.getScene().getWindow();
        stage.close();
    }

    //WIRD ÜBERARBEITET!
    //TODO!!! Finde einen Sql code für das checken des eingegebenen usernames und password
    // welches in eines der 3 Table (lehrer, schüler, admin) existiert und öffne dann
    // das geeignete fenster (lehrer,schüler, admin)!!!

    public void validateLogin() throws IOException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        //TODO use the "join" method in sql to combine the tables and
        // get username/ password to get the specific window!!!.

        String adminLogin = "SELECT count(1) FROM administrator WHERE username = '" + usernametxtfield.getText() + "' AND password = '" + passwordtxtfield.getText() + "'";
        //String verifyLogin = "SELECT count(1) FROM instructor WHERE username = '" + usernametxtfield.getText() + "' AND password = '" + passwordtxtfield.getText() + "'";
        //String getRole = "SELECT * FROM useraccounts WHERE role LIKE '%" + signup.roletxtfield + "%'" ;
        try {
            Statement statement = connectDB.createStatement();
            //ResultSet queryResult = statement.executeQuery(verifyLogin);
            ResultSet queryResultAdmin = statement.executeQuery(adminLogin);

            //Was ich hier versucht hab ist dass man die "ROLE" der hinzugefügten Person nimmt und somit dann
            //entweder für Role = "Student" die StudentView öffen und für
            //die Role = "Instructor" die InstructorView öffnen. Habe aber den Fehler:
            // "Operation not allowed after Resultset closed" erhalten. Suche für eine Behebung!
            //ResultSet roleResult = statement.executeQuery(getRole);

            while ( /*queryResult.next() || */ queryResultAdmin.next()) {
                //wenn das Acc existiert
                if (/*(queryResult.getInt(1) == 1) ||*/ (queryResultAdmin.getInt(1) == 1)) {

                    //ADMIN LOG IN DATA
                   if (usernametxtfield.getText().equals("admin1967") && passwordtxtfield.getText().equals("imtheadmin2361")) {
                       //Ändere auf Admin wenn admin einloggt.
                       root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
                       stage = (Stage) loginbtn.getScene().getWindow();
                       stage.setScene(new Scene(root, 520, 400));
                       stage.show();
                   }
                } //else if (roleResult.getInt(1) == 1){
                    //root = FXMLLoader.load(getClass().getResource("StudentView.fxml"));
                    //stage = (Stage) loginbtn.getScene().getWindow();
                    //stage.setScene(new Scene(root, 520, 400));
                    //stage.show();
                else {
                    loginmissinglabel.setText("Invalid Login");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}