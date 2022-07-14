package com.sample.fahrschuleapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Objects;

public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "drivingschool";
        String databaseUser = "root";
        String databasePassword = "";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
    public String getUserData() {
        String query = "SELECT Role, FirstName, SurName, Age, Email, Phonenumber, Username, Password FROM instructor\n" +
                "UNION\n" +
                "SELECT Role, FirstName, SurName, Age, Email, Phonenumber, Username, Password FROM student";

        return query;
    }

    public String getStudentData() {
        String query = "SELECT FirstName, SurName, Age, Email, DrivingLicenceType, DrivingLesson FROM student;";
        return query;
    }

    public String deleteStudent(String username) {
        String deleteUser = "DELETE FROM Student WHERE Username = '" + username + "';";
        return deleteUser;
    }

    public String deleteInstructor(String username) {
        String deleteUser = "DELETE FROM Instructor WHERE Username = '" + username + "';";
        return deleteUser;
    }

    public void registerInstructor(String firstname, String surname, String age, String email,
                                     String phonenumber, String gender, String salary, String username, String password) {

        String insertFields = "INSERT INTO instructor (FirstName, SurName, Age, Email, Phonenumber, Gender, Salary, Username, Password, Role) VALUES ('";
        String insertValues = firstname + "','" + surname + "','" + age + "','" + email + "','" + phonenumber + "','" + gender + "','" + salary + "','" + username + "','" + password + "','Instructor');";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(insertToRegister);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void registerStudent(String firstname, String surname, String age, String email, String phonenumber,
                                  String gender, String price, String drivingLicence, String username, String password, String InfoInstructor) {

        String insertFields = "INSERT INTO student (FirstName, SurName, Age, Email, Phonenumber, " +
                "Gender, Price, DrivingLicenceType, Username, Password, Role, InfoInstructor) VALUES ('";
        String insertValues = firstname + "','" + surname + "','" + age + "','" + email + "','" + phonenumber + "','" + gender + "','" + price + "','" + drivingLicence + "','" + username + "','" + password + "','Student', '" + InfoInstructor + "');";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(insertToRegister);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void updateInstructor(String firstname, String surname, String age, String email, String phonenumber,
                                    String username, String password) {

        String updateFields = "UPDATE Instructor SET FirstName = '"+ firstname +" ', SurName = '"+ surname +" ', Age = '"+ age
                +"', Email = '"+ email +" ', Phonenumber = '"+ phonenumber +"', Password = ' " + password + " ' WHERE username = '" + username+"';\n";

        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(updateFields);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void updateStudent(String firstname, String surname, String age, String email, String phonenumber,
                                   String username, String password) {

        String updateFields = "UPDATE Student SET FirstName = '"+ firstname +" ', SurName = '"+ surname +" ', Age = '"+ age
                +"', Email = '"+ email +" ', Phonenumber = '"+ phonenumber +"', Password = ' " + password + " ' WHERE username = '" + username+"';\n";

        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(updateFields);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    //Get and Updata DrivingLesson (hour) for Student in "AddDrivingLessonPage"

    public void updateCurrentDrivingLesson(String updatedValue, String username) {
        String updatefield = "Update Student SET DrivingLesson = ' "+updatedValue+"' WHERE username = '"+ username +"';";

        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(updatefield);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
