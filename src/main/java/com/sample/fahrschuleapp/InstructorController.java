package com.sample.fahrschuleapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class InstructorController implements Initializable {

    @FXML
    private Button applyButton, cancelButton, showInfoButton;
    @FXML
    private Button logoutbtn;
    @FXML
    private TextField firstnametxtfield, surnametxtfield, agetxtfield, emailtxtfield;
    @FXML
    private TextField phonenumbertxtfield, gendertxtfield, usernametxtfield, passwordtxtfield;


    private Stage stage;
    private Scene scene;
    private Parent root;

    DatabaseConnection dc = new DatabaseConnection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = firstnametxtfield.getText();
        String surname = surnametxtfield.getText();
        String age = agetxtfield.getText();
        String email = emailtxtfield.getText();
        String phonenumber = phonenumbertxtfield.getText();
        String gender = gendertxtfield.getText();
        String username = usernametxtfield.getText();
        String password = passwordtxtfield.getText();

        String updated = dc.updateInstructor(firstname, surname, age, email, phonenumber, gender, username, password);

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updated);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


}
