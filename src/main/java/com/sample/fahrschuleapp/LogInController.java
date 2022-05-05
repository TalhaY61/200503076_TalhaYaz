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
    public void validateLogin() throws IOException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM useraccounts WHERE username = '" + usernametxtfield.getText() + "' AND password = '" + passwordtxtfield.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {

                    //Habe bis jetzt nur 3 Default User erstellt, jeweils 1 Admin, 1 Student und 1 Lehrer.
                    //Habe Sign-UP fenster noch nicht hinbekommen. Arbeite dran.
                   if (usernametxtfield.getText().equals("admintalha61") && passwordtxtfield.getText().equals("fahrschuleyaz23")) {
                       //Ändere auf Admin wenn admin einloggt.
                       root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
                       stage = (Stage) loginbtn.getScene().getWindow();
                       stage.setScene(new Scene(root, 520, 400));
                       stage.show();

                    } else if (/*wenn schüler sich einloggt*/ usernametxtfield.getText().equals("kuzeytekinoglu01") && passwordtxtfield.getText().equals("babakuzey1997")){
                       //SchülerUsername werden mit "stu" anfangen, wenn die ersten 3 Buchstaben des Usernames der eingegeben Person mit
                       // "stu" anfangen dann student fenster öffnen.
                       root = FXMLLoader.load(getClass().getResource("StudentView.fxml"));
                       stage = (Stage) loginbtn.getScene().getWindow();
                       stage.setScene(new Scene(root, 520, 400));
                       stage.show();
                   } else if (usernametxtfield.getText().equals("handeyilmaz1986") && passwordtxtfield.getText().equals("handehande28")){
                       //LehrerUsername werden mit "Ins" anfangen, wenn die ersten 3 Buchstaben des eingegeben Usernamers mit
                       //"Ins" anfagen dann lehrer fenster öffnen.
                       root = FXMLLoader.load(getClass().getResource("InstructorView.fxml"));
                       stage = (Stage) loginbtn.getScene().getWindow();
                       stage.setScene(new Scene(root, 520, 400));
                       stage.show();
                   }
                } else {
                    loginmissinglabel.setText("Invalid Login");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}