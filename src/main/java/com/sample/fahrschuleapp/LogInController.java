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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;
import com.sample.fahrschuleapp.SignUpController;

import javax.xml.transform.Result;


public class LogInController implements Initializable {
    @FXML
    private Button loginbtn;

    @FXML
    private Button exitButton;

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
    protected void exitButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin() throws IOException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        //Wenn Admin einloggt.
        if (usernametxtfield.getText().equals("root") && passwordtxtfield.getText().equals("2361")) {
            //Ändere auf Admin wenn admin einloggt.
            root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
            stage = (Stage) loginbtn.getScene().getWindow();
            stage.setScene(new Scene(root, 520, 400));
            stage.show();
        }

        String verifyLogin = "SELECT count(1) FROM instructor WHERE username = '" + usernametxtfield.getText() + "';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {

                //wenn der User existiert
                if ((queryResult.getInt(1) == 1)) {

                    //Da außer Admin nur Lehrer loggen kann, öffnet sich die InstructorView Page!
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InstructorView.fxml")));
                    stage = (Stage) loginbtn.getScene().getWindow();
                    stage.setScene(new Scene(root, 520, 400));
                    stage.show();
                } else {
                    loginmissinglabel.setText("Invalid Login!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}