package com.sample.fahrschuleapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loginButtonOnAction(ActionEvent event) {

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

    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = ' " + usernametxtfield.getText() + " ' AND password = '" + passwordtxtfield.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginmissinglabel.setText("Congratulations");
                    //ändere Fenster zu admin glaube ich.
                } else {
                    loginmissinglabel.setText("Invalid Login. Please Try Again!");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}