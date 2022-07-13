package com.sample.fahrschuleapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage.setTitle("Fahrschule YAZ");
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}